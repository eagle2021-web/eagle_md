import datetime
import http.client
import os.path
import threading
from pathlib import Path
from typing import Union
from unittest import TestCase
from urllib.parse import urlparse
import requests
import urllib3
from tqdm import tqdm
from time import localtime
import sys

lock = threading.Lock()


def download_chunk(url, start, end, file):
    headers = {'Range': f'bytes={start}-{end}'}
    res = requests.get(url, headers=headers, stream=True)
    for chunk in res.iter_content(chunk_size=1024):
        if chunk:
            file.write(chunk)


def download_file(url, num_threads=4):
    res = requests.head(url)
    total_size = int(res.headers.get('Content-Length', 0))
    chunk_size = total_size // num_threads

    with open('myfile.zip', 'wb') as file:
        threads = []
        for i in range(num_threads):
            start = i * chunk_size
            end = (i + 1) * chunk_size - 1 if i < num_threads - 1 else ''
            thread = threading.Thread(target=download_chunk, args=(url, start, end, file))
            threads.append(thread)
            thread.start()

        for thread in threads:
            thread.join()

    print("Download complete!")


# 调用函数并传入要下载的文件URL和使用的线程数

def create_directory(abs_dir):
    # 获取锁
    lock.acquire()
    try:
        if not Path(abs_dir).exists():
            Path(abs_dir).mkdir(parents=True)
            print(f"目录 {abs_dir} 创建成功")
        else:
            print(f"目录 {abs_dir} 已存在")
    finally:
        # 释放锁
        lock.release()


class HttpMod:
    def __init__(self, **kwargs):
        self.host = kwargs.get("host")
        self.port = kwargs.get("port")
        self.filename = kwargs.get("filename")
        self.path = kwargs.get('path')
        self.rest_url = kwargs.get('rest_url')

    @classmethod
    def parse_url(cls, url: str):
        parsed_url = urlparse(url)
        arr = parsed_url.path.split('/')
        rest_url = f'{parsed_url.path}?{parsed_url.query}'

        print(arr)
        return HttpMod(host=parsed_url.hostname,
                       port=parsed_url.port,
                       filename=arr[-1],
                       path=parsed_url.path,
                       rest_url=rest_url)


class DownloadKits:

    @classmethod
    def download_by_requests(cls, url: str, num_segments=4):
        mod = HttpMod.parse_url(url)
        response = requests.head(url)
        total_size = int(response.headers.get("Content-Length"))
        segment_size = total_size // num_segments
        print(total_size)
        print(segment_size)
        progress_bar = tqdm(total=total_size, unit="B", unit_scale=True)
        # 获取当前本地时间
        local_time = datetime.datetime.now()

        # 打印本地时间
        print(local_time)
        if response.status_code == 200:
            with open(mod.filename, 'wb') as file:
                for i in range(num_segments):
                    start_byte = i * segment_size
                    end_byte = start_byte + segment_size - 1 if i < num_segments - 1 else ''
                    print('----')
                    print(start_byte)
                    print(end_byte)
                    headers = {"Range": f"bytes={start_byte}-{end_byte}"}
                    response = requests.get(url, headers=headers, stream=True)

                    for chunk in response.iter_content(1024):
                        file.write(chunk)
                        progress_bar.update(len(chunk))

            print('文件下载完成')
        else:
            print('文件下载失败')
        local_time = datetime.datetime.now()

        # 打印本地时间
        print(local_time)

    @classmethod
    def download_by_http_client(cls, url: str, rel_dir: Union[Path, str] = 'abc'):
        mod = HttpMod.parse_url(url)
        abs_dir = Path(os.path.abspath(rel_dir))
        create_directory(abs_dir)
        abs_file = abs_dir.joinpath(mod.filename)
        size = 0
        if abs_file.exists():
            size = os.path.getsize(abs_file)
        print(f"初始文件大小为: {size} 字节")
        conn = http.client.HTTPSConnection(mod.host)
        payload = ''
        headers = {f"Range": f"bytes={size}-"}
        conn.request("GET", mod.rest_url, payload, headers)
        res = conn.getresponse()
        total_size = int(res.headers.get("Content-Length"))  # 获取文件总大小
        print(f'total_size = {total_size}')
        chunk_size = 1024 * 128

        with open(abs_file, 'ab') as f:
            progress_bar = tqdm(total=total_size, unit="B", unit_scale=True)  # 创建进度条对象
            while True:
                chunk = res.read(chunk_size)
                if chunk:
                    f.write(chunk)
                    progress_bar.update(len(chunk))  # 更新进度条
                else:
                    break
            progress_bar.close()  # 关闭进度条

        print("下载完成")
        res.close()
        conn.close()

    @classmethod
    def download_by_pool(cls, url):
        mod = HttpMod.parse_url(url)
        # 创建连接池管理器
        pool = urllib3.PoolManager()
        # 发送 GET 请求并获取响应对象
        response = pool.request("GET", url, preload_content=False)
        # 获取文件总大小
        total_size = int(response.headers.get("Content-Length"))
        # 初始化进度条
        progress_bar = tqdm(total=total_size, unit="B", unit_scale=True)
        # 写入文件
        with open(mod.filename, 'wb') as f:
            for chunk in response.stream(1024):
                f.write(chunk)
                progress_bar.update(len(chunk))  # 更新进度条
        # 关闭进度条和响应对象
        progress_bar.close()
        response.release_conn()
        print("下载完成")

    @classmethod
    def send_head_request(cls, url):
        parsed_url = urlparse(url)
        conn = http.client.HTTPSConnection(parsed_url.netloc)
        conn.connect()
        conn.request("HEAD", parsed_url.path + "?" + parsed_url.query)
        response = conn.getresponse()
        print("Status:", response.status)
        print("Headers:")
        for header in response.getheaders():
            print(header[0], ":", header[1])
        conn.close()


def task2():
    url = 'https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1'
    DownloadKits.download_by_http_client(url, 'abc3')
    print('2')


def task1():
    url = 'https://www.zenodo.org/record/8104287/files/python-pillow/Pillow-10.0.0.zip?download=1'
    DownloadKits.download_by_http_client(url, 'abc3')
    print('1')


def task3():
    url = 'https://www.zenodo.org/record/8040203/files/go-release-archive.tgz?download=1'
    DownloadKits.download_by_http_client(url, 'abc3')
    print('3')


# 创建并启动第一个线程
# if __name__ == '__main__':
#     thread1 = threading.Thread(target=task1)
#     thread1.start()
#
#     # 创建并启动第二个线程
#     thread2 = threading.Thread(target=task2)
#     thread2.start()
#
#     # 主线程继续执行其他任务
#     print("This is the main thread.")
#
#     # 等待两个线程结束
#     thread1.join()
#     thread2.join()
#
#     # 主线程执行完毕
#     print("Main thread is done.")

class TestDownload(TestCase):
    def setUp(self) -> None:
        self.url = 'https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1'

    def test_head(self):
        DownloadKits.send_head_request(self.url)

    def test_download_file(self):
        download_file('https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1',
                      num_threads=4)

    def test_log(self):
        from tqdm import tqdm
        import time

        # 模拟一个循环进行某个任务
        for i in tqdm(range(10)):
            # 执行任务的代码
            time.sleep(1)  # 模拟任务执行时间

        # 输出完成信息
        print("任务完成")

    def testaaaaaa(self):
        thread1 = threading.Thread(target=task1)
        thread1.start()

        # 创建并启动第二个线程
        thread2 = threading.Thread(target=task2)
        thread2.start()
        thread3 = threading.Thread(target=task3)
        thread3.start()
        # 主线程继续执行其他任务
        print("This is the main thread.")

        # 等待两个线程结束
        thread1.join()
        thread2.join()
        thread3.join()
        print("Main thread is done.")

    def test_download_by_client(self):
        url = 'https://www.zenodo.org/record/8104287/files/python-pillow/Pillow-10.0.0.zip?download=1'
        DownloadKits.download_by_http_client(url)

    def test_download_by_client2(self):
        url = 'https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1'
        DownloadKits.download_by_http_client(url, 'abc11')

    def test_download_by_request(self):
        DownloadKits.download_by_requests(self.url)

    def test_download_pool(self):
        DownloadKits.download_by_pool(self.url)



if sys.stdout.encoding != 'UTF-8':
    sys.stdout = open(sys.stdout.fileno(), 'w', encoding='UTF-8', closefd=False)
if sys.stderr.encoding != 'UTF-8':
    sys.stderr = open(sys.stderr.fileno(), 'w', encoding='UTF-8', closefd=False)


class MyTestCase(unittest.TestCase):
    def setUp(self):
        super().setUp()

    def test_something(self):
        # 测试逻辑...
        print(12)
