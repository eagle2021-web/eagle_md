from unittest import TestCase

from models.field import Fields


class HttpMod:
    def __init__(self):
        self.host = None
        self.port = '80'
        self.rest_url = ''

    def __str__(self):
        return self.__dict__


class HttpKits:
    datasets_tree = '/datasets/bigcode/the-stack/tree'
    datasets_resolve = '/datasets/bigcode/the-stack/resolve'

    @classmethod
    def get_prefix_datasets_tree_with_branch(cls, branch: str):
        return f'{cls.datasets_tree}/{branch}'

    @classmethod
    def get_prefix_datasets_data_url(cls, branch: str):
        return f'{cls.datasets_tree}/{branch}/{Fields.data}'

    @classmethod
    def get_resolve_prefix_with_sub_dir(cls, branch: str, sub_dir: str):
        return f'{cls.datasets_resolve}/{branch}/{Fields.data}/{sub_dir}'

    @classmethod
    def extract_msg_from_url(cls, url: str) -> HttpMod:
        arr = url.replace('//', '/').split('/', 2)
        h = HttpMod()
        h.host = arr[1]
        h.rest_url = arr[2]
        return h


class TestHttpKits(TestCase):
    def test_extract_msg_from_url(self):
        url = 'https://huggingface.co/api/datasets/bigcode/the-stack/tree/v1.2/data?cursor=ZXlKbWFXeGxYMjVoYldVaU9pSmtZWFJoTDJOdFlXdGxJbjA9OjUw&expand=true'
        h = HttpKits.extract_msg_from_url(url)

        print(h.host)
        print(h.rest_url)
