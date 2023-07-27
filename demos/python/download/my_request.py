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
    def get_total_size(cls, url) -> int:
        response = requests.head(url)
        response.raise_for_status()
        total_size = int(response.headers.get("content-length", 0))
        return total_size
    
    @classmethod
    def check_file_size_integrity(cls, abs_file: Path, totol_size):
        size = 0
        if abs_file.exists():
            size = os.path.getsize(abs_file)
        else:
            parent = Path(abs_file.parent)
            if not parent.exists():
                parent.mkdir(parents=True)
            with open(abs_file, 'ab') as f:
                pass
        return size == total_size

    @classmethod
    def download(cls, url: str, rel_dir: str, max_thread=4):
        mod = HttpMod.parse_url(url)
        local_mod = LocalFileMod.create_mod(rel_dir=rel_dir, filename=mod.filename)
        abs_file = Path(local_mod.abs_file_path)
        total_size = cls.get_total_size(url=url)
        FileUtils.create_file(abs_file=abs_file, total_size=total_size)
        divided_list = Partition.get_div_list(total_size=total_size,  max_thread=max_thread)
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
            print('等待所有任务执行完毕')
            print(len(futures))
            as_completed(futures)
            print('所有任务执行完毕')
        print('here')            
        thread.join()
        progress.stop()
        sleep(1)

    @classmethod
    def range_download(cls, url, abs_path, s_pos, e_pos, progress: Progress, task, queue: Queue, idx: int):
        begin = s_pos
        while True:
            retry = 0
            try:
                headers = {f"Range": f"bytes={s_pos}-{e_pos}"}
                # response = requests.get(url, stream=True, headers=headers)
                # response.raise_for_status()
                abs_file = Path(abs_path)
                print(f'下载从{s_pos}到{e_pos}')
                offset = s_pos
                with requests.get(url, stream=True, headers=headers) as response:
                    for chunk in response.iter_content(chunk_size=1024 * 256):
                        if not chunk:
                            break
                        progress.advance(task, advance=len(chunk))         
                        queue.put((None, chunk, len(chunk), offset, idx))
                        offset += len(chunk)
                queue.put((True, None, None, None, None))
                # response.close()
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
if __name__ == '__main__':
    url = "https://www.zenodo.org/api/files/fafb41f4-4679-4ade-8ec6-1e48ecd43072/mentions.zip"
    url = 'https://www.zenodo.org/record/7834392/files/full_dataset.tsv.gz.part-aa?download=1'
    total_size = Requestor.download(url=url, rel_dir='a19', max_thread=10)

