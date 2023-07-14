# !/usr/bin/env python
# coding = utf-8
import http
import unittest
from concurrent.futures import ThreadPoolExecutor, as_completed
from http.client import HTTPConnection
from pathlib import Path

from tqdm import tqdm

from models.httpMod import HttpMod
from models.localFileMod import LocalFileMod
import os


class HttpClientConnector:
    def __init__(self, **kwargs) -> None:
        print(kwargs)
        self.session = kwargs.get("session")
        hc = HTTPConnection()
        hc.connect("www.")

    @classmethod
    def connect(cls, **kwargs):
        return cls(**kwargs)

    @classmethod
    def download(cls, url: str, rel_dir: str, max_thread: int = 4):
        mod = HttpMod.parse_url(url)
        local_mod = LocalFileMod.create_mod(rel_dir=rel_dir, filename=mod.filename)
        abs_file = Path(local_mod.abs_file_path)
        size = 0
        conn, res = None, None
        if abs_file.exists():
            size = os.path.getsize(abs_file)
        else:
            Path(abs_file.parent).mkdir(parents=True)
            with open(abs_file, 'ab') as f:
                pass
        print(f"初始文件大小为: {size} 字节")
        divided_list = cls.get_div_list(mod, size, max_thread)
        if divided_list is None:
            return
        with ThreadPoolExecutor() as p:
            futures = []
            for s_pos, e_pos in divided_list:
                print(s_pos, e_pos)
                futures.append(p.submit(cls.range_download, url, local_mod.abs_file_path, s_pos, e_pos))
            # 等待所有任务执行完毕
            as_completed(futures)

    @classmethod
    def get_div_list(cls, mod, init_size, max_thread: int):
        conn, res = None, None
        try:
            payload = ''
            headers = {f"Range": f"bytes=0-"}
            conn = http.client.HTTPSConnection(mod.host)
            conn.request("HEAD", mod.rest_url, payload, headers)
            res = conn.getresponse()
            total_size = int(res.headers.get("Content-Length"))  # 获取文件总大小
            print(f'total_size = {total_size}')
            if total_size <= init_size:
                return list()
            rest_size = total_size - init_size
            # 100M一个
            thread_num = int(rest_size / (100 * 1024)) + 1
            if thread_num > max_thread:
                thread_num = max_thread
            step = rest_size // thread_num
            divided_list = []
            for i in range(thread_num):
                s_pos = init_size + step * i
                e_pos = init_size + step * (i + 1) - 1
                divided_list.append([s_pos, e_pos])
            divided_list[-1][1] = total_size - 1
            print(divided_list)
            return divided_list
        except Exception as e:
            print(e)
        finally:
            if res:
                res.close()
            if conn:
                conn.close()

    @classmethod
    def range_download(cls, url, abs_path, s_pos, e_pos):
        while True:
            retry = 0
            try:
                mod = HttpMod.parse_url(url)
                abs_file = Path(abs_path)
                conn = http.client.HTTPSConnection(mod.host)
                payload = ''
                headers = {f"Range": f"bytes={s_pos}-{e_pos}"}
                conn.request("GET", mod.rest_url, payload, headers)
                res = conn.getresponse()
                total_size = int(res.headers.get("Content-Length"))  # 获取文件总大小
                print(f'total_size = {total_size}')
                chunk_size = 1024 * 128
                print(f'下载从{s_pos}到{e_pos}')
                with open(abs_file, 'rb+') as f:
                    f.seek(s_pos)
                    progress_bar = tqdm(total=e_pos - s_pos, unit="B", unit_scale=True)  # 创建进度条对象
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
            except Exception as e:
                print(f"异常{str(abs_file)}分片：{s_pos}-{e_pos}")
                retry += 1
                if retry == 4:
                    break


class Testa(unittest.TestCase):
    def testa(self):
        url = 'https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1'
        mod = HttpMod.parse_url(url)
        print(mod.__dict__)
        HttpClientConnector.get_div_list(mod, 0)

    def test_download(self):
        url = 'https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1'
        mod = HttpMod.parse_url(url)
        print(mod.__dict__)
        HttpClientConnector.download(url, "a12", 4)
# if __name__ == "__main__":
# HttpClientConnector.connect(**{"name": "adsf", "age": 1})
