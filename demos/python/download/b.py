import requests
from concurrent.futures import ThreadPoolExecutor, as_completed
from tqdm import tqdm


def get_file_size(url):
    response = requests.head(url)
    return int(response.headers.get("content-length", 0))


def download_file(url, filename):
    response = requests.get(url, stream=True)
    total_size = get_file_size(url)
    chunk_size = 1024
    progress = tqdm(total=total_size, unit="B", unit_scale=True)

    with open(filename, "wb") as file:
        for chunk in response.iter_content(chunk_size=chunk_size):
            if chunk:
                file.write(chunk)
                progress.update(len(chunk))

    progress.close()


if __name__ == '__main__':
    # 定义下载链接和文件名
    url = "https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1"
    filename = "Trixi.jl-v0.5.30.zip"

    # 获取文件大小
    total_size = get_file_size(url)

    # 创建线程池
    with ThreadPoolExecutor() as executor:
        # 提交下载任务
        future = executor.submit(download_file, url, filename)

        # 显示总进度条
        with tqdm(total=total_size, unit="B", unit_scale=True, ncols=80) as pbar:
            while not future.done():
                downloaded_size = get_file_size(url)
                pbar.n = downloaded_size
                pbar.refresh()

        # 等待下载任务完成
        future.result()