from utils.cookie import CookieKits


class Downloader:
    @classmethod
    def download_with_token(cls):
        from huggingface_hub import hf_hub_download

        print(1)
        # hf_hub_download(repo_id="lysandre/arxiv-nlp", filename="config.json")
        # print(2)
        # hf_hub_download(repo_id="google/fleurs", filename="fleurs.py", repo_type="dataset")
        print(3)
        # hf_hub_download(repo_id="bigcode/the-stack", filename="data/abap/train-00000-of-00001.parquet", repo_type="dataset"
        #                 , token='')

        # hf_hub_download(repo_id="bigcode/the-stack", filename="README.md", repo_type="dataset",
        # token='')
        hf_hub_download(
            repo_id="bigcode/the-stack",
            filename="data/abap/train-00000-of-00001.parquet",
            repo_type="dataset",
            token=''
        )
        print(4)

# https://huggingface.co/datasets/bigcode/the-stack/resolve/main/data/abap/train-00000-of-00001.parquet
if __name__ == '__main__':
    cookie = CookieKits.get_data_html_cookie()
    import http.client

    conn = http.client.HTTPSConnection("huggingface.co")
    payload = ''
    headers = {
        'Cookie': cookie
    }
    conn.request("GET", "/datasets/bigcode/the-stack/resolve/main/data/abap/train-00000-of-00001.parquet", payload,
                 headers)
    res = conn.getresponse()
    location = res.getheader('Location')
    print(f'location = {location}')
    data = res.read()
    print(data.decode("utf-8"))