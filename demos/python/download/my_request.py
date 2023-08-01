import hashlib
import http
from unittest import TestCase

import http_client
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
from partition import Partition
from time import sleep
from file_utils import FileUtils
from threading import Lock
from queue import Queue
import threading

# 在类的外部定义一个锁
file_lock = Lock()


class Requestor:
    def __init__(self) -> None:
        pass

    @classmethod
    def get_con(cls, url, method, body, headers):
        print(1)
        mod = HttpMod.parse_url(url)
        hc = http_client.HTTPSConnection('www.zenodo.org')
        hc.request(url=mod.rest_url, method=method, body=body, headers=headers)
        return hc

    @classmethod
    def get_total_size(cls, url) -> int:
        mod = HttpMod.parse_url(url)
        response = requests.head(url)
        response.raise_for_status()
        hc = cls.get_con(url, method='HEAD', body='', headers={})
        res = hc.getresponse()
        size = int(res.getheader('content-length'))
        print(f'size = {size}')
        return size
    
    @classmethod
    def cnt_md5(cls, abs_path):
        md5 = hashlib.md5()
        with open(abs_path, 'rb') as f:
            while True:
                data = f.read(4096)
                if not data:
                    break
                md5.update(data)
        md5_str = md5.hexdigest()
        print(f'md5_str = {md5_str}')
        return md5_str
    
    @classmethod
    def download(cls, url: str, rel_dir: str, max_thread=4):
        mod = HttpMod.parse_url(url)
        local_mod = LocalFileMod.create_mod(rel_dir=rel_dir, filename=mod.filename)
        abs_file = Path(local_mod.abs_file_path)
        total_size = cls.get_total_size(url=url)
        FileUtils.create_file(abs_file=abs_file, total_size=total_size)
        divided_list = Partition.get_div_list(total_size=total_size, max_thread=max_thread)
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
            task = progress.add_task(description=f"[cyan]sec_{s_pos}_{e_pos}...", total=length)
            task_list.append(task)
        progress.start()
        idx = 0
        futures = []
        print(url)
        queue = Queue()
        thread = threading.Thread(target=cls.write_file, args=(abs_file, queue, len(task_list)))
        thread.start()
        with ThreadPoolExecutor() as p:
            for s_pos, e_pos in divided_list:
                print(s_pos, e_pos)
                cur_task = task_list[idx]
                idx += 1
                futures.append(p.submit(cls.range_download, url, local_mod.abs_file_path, s_pos, e_pos,
                                        progress, cur_task, queue, idx))
            # 等待所有任务执行完毕
            print(len(futures))
            as_completed(futures)
        print('here')
        thread.join()
        progress.stop()
        cls.cnt_md5(abs_path=abs_file)
        sleep(1)

    @classmethod
    def range_download(cls, url, abs_path, s_pos, e_pos, progress: Progress, task, queue: Queue, idx: int):
        begin = s_pos
        abs_file = Path(abs_path)
        offset = s_pos
        hc, res = None, None
        while True:
            retry = 0
            try:
                headers = {f"Range": f"bytes={s_pos}-{e_pos}"}
                print(f'下载从{s_pos}到{e_pos}')
                offset = s_pos
                hc = cls.get_con(url, method='GET', body='', headers=headers)
                res = hc.getresponse()
                chunk_size = 1024 * 256
                while True:
                    chunk = res.read(chunk_size)
                    if chunk:
                        progress.advance(task, advance=len(chunk))
                        queue.put((None, chunk, len(chunk), offset, idx))
                        offset += len(chunk)
                    else:
                        break
                queue.put((True, None, None, None, None))
                break
            except Exception as e:
                print(e)
                print(f"异常{str(abs_file)}分片：{s_pos}-{e_pos}")
                print(f'下一个开始于{offset}')
                progress.update(task, completed=offset - begin)
                s_pos = offset
                retry += 1
                if retry == 4:
                    break
                sleep(5)
            finally:
                if res:
                    res.close()
                if hc:
                    hc.close()

    @classmethod
    def write_file(cls, abs_file: Path, queue: Queue, end_cnt: int):
        done_count = 0
        with open(abs_file, 'rb+') as f:
            while done_count < end_cnt:
                if queue.empty():
                    sleep(0.3)
                    continue
                ok, data, size, offset, idx = queue.get()
                if ok:
                    done_count += 1
                else:
                    f.seek(offset)
                    f.write(data)





