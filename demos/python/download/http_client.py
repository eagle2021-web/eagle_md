# !/usr/bin/env python
# coding = utf-8
import http
import unittest
from concurrent.futures import ThreadPoolExecutor, as_completed
from http.client import HTTPConnection, HTTPSConnection
from pathlib import Path

import requests
from rich.progress import Progress
from tqdm import tqdm

from models.httpMod import HttpMod
from models.localFileMod import LocalFileMod
import os
from time import sleep
from rich.progress import (
    BarColumn,
    DownloadColumn,
    Progress,
    SpinnerColumn,
    TaskProgressColumn,
    TimeElapsedColumn,
    TimeRemainingColumn,
    TextColumn,
    TransferSpeedColumn
)
class HttpClientConnector:
    def __init__(self, **kwargs) -> None:
        print(kwargs)

    @classmethod
    def connect(cls, **kwargs):

        return cls(**kwargs)

    @classmethod
    def get_zenodo_connection(cls, host="www.zenodo.org"):
        hc = HTTPSConnection(host)
        return hc

    @classmethod
    def create_file(cls, abs_file: Path) -> int:
        size = 0
        if abs_file.exists():
            size = os.path.getsize(abs_file)
        else:
            parent = Path(abs_file.parent)
            if not parent.exists():
                parent.mkdir(parents=True)
            with open(abs_file, 'ab') as f:
                pass
        return size

    def download(self, url: str, rel_dir: str, max_thread: int = 4):
        mod = HttpMod.parse_url(url)
        local_mod = LocalFileMod.create_mod(rel_dir=rel_dir, filename=mod.filename)
        abs_file = Path(local_mod.abs_file_path)
        init_size = self.create_file(abs_file)
        total_size, divided_list = self.get_div_list(mod, init_size, max_thread)
        if divided_list is None:
            return
        progress = Progress(TextColumn("[progress.description]{task.description}"),
                    SpinnerColumn(),
                    BarColumn(),
                    # FileSizeColumn(),
                    # TotalFileSizeColumn(),
                    DownloadColumn(),
                    # TransferSpeedColumn(),
                    TransferSpeedColumn(),
                    TextColumn("[progress.percentage]{task.percentage:>3.0f}%"),
                    TimeRemainingColumn(),
                    TimeElapsedColumn(),
                    refresh_per_second=0.3)

        # 添加一个任务，指定任务总数
        task_list = []
        for s_pos, e_pos in divided_list:
            length = e_pos - s_pos + 1
            task = progress.add_task(description=f"[cyan]sec__{s_pos}_{e_pos}...", total=length)
            task_list.append(task)
        progress.start()
        idx = 0
        futures = []
        with ThreadPoolExecutor() as p:
            for s_pos, e_pos in divided_list:
                print(s_pos, e_pos)
                cur_task = task_list[idx]
                idx += 1
                futures.append(p.submit(self.range_download, url, local_mod.abs_file_path, s_pos, e_pos,
                                        progress, cur_task))
            # 等待所有任务执行完毕
            as_completed(futures)
        progress.stop()
        sleep(1)

    @classmethod
    def get_div_list(cls, mod, init_size, max_thread: int = 3):
        conn, res = None, None
        try:
            payload = ''
            headers = {f"Range": f"bytes=0-"}
            conn = cls.get_zenodo_connection()
            conn.request("HEAD", mod.rest_url, payload, headers)
            res = conn.getresponse()
            total_size = int(res.headers.get("Content-Length"))  # 获取文件总大小
            print(f'total_size = {total_size}')
            if total_size <= init_size:
                return total_size, list()
            rest_size = total_size - 0
            # 100M一个
            thread_num = int(rest_size / (100 * 1024)) + 1
            if thread_num > max_thread:
                thread_num = max_thread
            step = rest_size // thread_num
            divided_list = []
            init_size = 0
            for i in range(thread_num):
                s_pos = init_size + step * i
                e_pos = init_size + step * (i + 1) - 1
                divided_list.append([s_pos, e_pos])
            divided_list[-1][1] = total_size - 1
            print(divided_list)
            return total_size, divided_list
        except Exception as e:
            print(e)
        finally:
            if res:
                res.close()
            if conn:
                conn.close()

    def range_download(self, url, abs_path, s_pos, e_pos, progress: Progress, task):
        while True:
            conn, res = None, None
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
                chunk_size = 1024 * 256
                print(f'下载从{s_pos}到{e_pos}')
                with open(abs_file, 'rb+') as f:
                    f.seek(s_pos)
                    while True:
                        chunk = res.read(chunk_size)
                        if chunk:
                            progress.advance(task, advance=len(chunk))
                            f.write(chunk)
                            # progress_bar.update(len(chunk))  # 更新进度条
                        else:
                            break
                break
            except Exception as e:
                print(f"异常{str(abs_file)}分片：{s_pos}-{e_pos}")
                progress.update(task, completed=0)  
                retry += 1
                if retry == 4:
                    break
            finally:
                if res:
                    res.close()
                if conn:
                    conn.close()


class Testa(unittest.TestCase):
    def testa(self):
        url = 'https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1'
        url = 'https://www.zenodo.org/api/files/fafb41f4-4679-4ade-8ec6-1e48ecd43072/mentions.zip'
        mod = HttpMod.parse_url(url)
        print(mod.__dict__)
        # HttpClientConnector.get_div_list(mod, 0)
        HttpClientConnector().download(url, "a14", 1)

    def test_download(self):
        url = "https://www.zenodo.org/api/files/fafb41f4-4679-4ade-8ec6-1e48ecd43072/mentions.zip"
        filename = "mentions.zip"

        response = requests.get(url, stream=True)
        response.raise_for_status()

        total_size = int(response.headers.get("content-length", 0))

        with open(filename, "wb") as file, tqdm(
            total=total_size, unit="B", unit_scale=True, unit_divisor=1024, desc=filename
        ) as progress:
            for chunk in response.iter_content(chunk_size=8192):
                file.write(chunk)
                progress.update(len(chunk))

    def test_proxy(self):
        proxies = {
            'http_proxy': 'http://localhost:10794',
            'https_proxy': 'http://localhost:10794'
        }
        import requests

        url = "https://huggingface.co/api/datasets/bigcode/the-stack/tree/v1.2/data?cursor=ZXlKbWFXeGxYMjVoYldVaU9pSmtZWFJoTDJOdFlXdGxJbjA9OjUw&expand=true"

        payload = {}
        headers = {
            'Cookie': '__stripe_mid=6c5439e4-6fc1-4976-b9c2-f4e41a930e8717fc08; token=QiaaglgFZGCxRsXIvHOjUkmUKaRAsvrzGUWzeJWIknctNUfiKuBXoOZzBevFENfWPymgOiIwRsFTavCaDzsqdPFpFCYNAQyJpgpqcNuhhwlrJEzOynURdtQSvSBVVyaN; _gid=GA1.2.2028637117.1685878985; _gat=1; _ga_8Q63TH4CSL=GS1.1.1685882033.4.0.1685882033.0.0.0; _ga=GA1.1.2108550687.1685700358; __stripe_sid=bf6aaed3-e2c1-482d-86f4-c6ea60811c90fa9f3a'
        }

        response = requests.request("GET", url, headers=headers, data=payload, proxies=proxies)

        print(response.text)

# if __name__ == "__main__":
# HttpClientConnector.connect(**{"name": "adsf", "age": 1})
