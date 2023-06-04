from typing import Dict, Optional
from unittest import TestCase

from utils.headers import HeaderKits
import http.client

from utils.httpKits import HttpKits


class Query:
    next_url = 'https://huggingface.co/api/datasets/bigcode/the-stack/tree/v1.2/data?' \
               'cursor=ZXlKbWFXeGxYMjVoYldVaU9pSmtZWFJoTDJOdFlXdGxJbjA9OjUw&expand=true'

    @classmethod
    def query_next_url(cls, next_url: Optional[str]):
        if next_url is None:
            return
        i = 0
        ok = False
        h = HttpKits.extract_msg_from_url(next_url)
        print('http msg ==')
        print(h.host)
        print(h.rest_url)
        while i < 10 and not ok:
            print(f'i = {i}')
            try:
                conn = http.client.HTTPSConnection(h.host)
                payload = ''
                headers = HeaderKits.get_next_url_headers()

                conn.request("GET",
                             f'/{h.rest_url}',
                             payload, headers)
                res = conn.getresponse()
                data = res.read()
                print(data.decode("utf-8"))
                res.close()
                ok = True
                print('ok')
            except Exception as e:
                print(f'err msg = {e}')
            i += 1


class TestQuery(TestCase):
    def test_query_next_url(self):
        Query.query_next_url(Query.next_url)
        print()

    def testa(self):
        import requests

        url = "https://huggingface.co/api/datasets/bigcode/the-stack/tree/v1.2/data?cursor=ZXlKbWFXeGxYMjVoYldVaU9pSmtZWFJoTDNOdmRYSmpaWEJoZDI0aWZRPT06MzAw&expand=true"

        payload = {}
        headers = {
        }

        response = requests.request("GET", url, headers=headers, data=payload)
        link_header: str = response.headers.get('Link')
        next_url = link_header.split('>')[0][1:]
        print(f'\"next_url\":\"{next_url}\"')
        print(response.text)

    def test_get_data_html(self):
        import http.client

        conn = http.client.HTTPSConnection("huggingface.co")
        payload = ''
        headers = {
        }
        conn.request("GET", "/datasets/bigcode/the-stack/tree/main/data", payload, headers)
        res = conn.getresponse()
        data = res.read()
        print(data.decode("utf-8"))
