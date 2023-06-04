from pathlib import Path
from typing import Union, Dict, List
from unittest import TestCase

from bs4 import BeautifulSoup

from fixtures import BASE_DIR
from models.field import Fields
from utils.file_kits import FileKits
from utils.httpKits import HttpKits
from urllib.parse import unquote, quote


class NetFileMod:
    def __init__(self):
        self.rest_url = None
        self.num = None
        self.storage_size = None

    def __str__(self):
        return self.__dict__


class HtmlKits:
    size_can = ['kb', 'mb', 'gb']

    @classmethod
    def parser_html_and_get_str_with_prefix(cls, content: str, prefix: str):
        soup = BeautifulSoup(content, 'html.parser')
        ret = list()
        for link in soup.find_all('a', href=True):
            href: str = link[Fields.href]
            if href.startswith(prefix) and not href.__eq__(prefix):
                ret.append(href)
        return ret

    @classmethod
    def extract_net_file_from_html_with_prefix(cls, content: str, prefix: str) -> List[NetFileMod]:
        prefix = quote(prefix)
        soup = BeautifulSoup(content, 'html.parser')
        ret = list()
        for link in soup.find_all('a', href=True):
            href: str = link[Fields.href]
            if not (href.startswith(prefix) and not href.__eq__(prefix)):
                continue
            text: str = link.text
            arr = text.splitlines()
            net_file = NetFileMod()
            net_file.rest_url = href
            for _v in arr:
                v = _v.strip().lower()
                if len(v) == 0 or 'lfs'.__eq__(v):
                    continue
                net_file.num, net_file.storage_size = v.split(' ', 1)
            ret.append(net_file)
        return ret

    @classmethod
    def extract_href_from_html(cls, file_path: Union[Path, str], prefix: str):
        path = BASE_DIR.joinpath(file_path)
        content = FileKits.read_file_and_get_str(path)
        return cls.parser_html_and_get_str_with_prefix(content, prefix)


class TestParser(TestCase):
    def test_v1_2_all_data_html(self):
        branch = 'main'
        path = BASE_DIR.joinpath(f'fixtures/htmls/{branch}/all_data.html')
        content = FileKits.read_file_and_get_str(path)
        arr = HtmlKits.parser_html_and_get_str_with_prefix(content,
                                                           HttpKits.get_prefix_datasets_tree_with_branch(branch))
        prefix = HttpKits.get_prefix_datasets_data_url(branch)
        prefix_len = len(prefix)
        ret = []
        for v in arr:
            rel_dir = v[prefix_len:]
            decoded_url = unquote(rel_dir)
            ret.append(decoded_url)
        FileKits.write_json('tmp.json', ret)

    def test_parser_html(self):
        branch = 'v1.2'
        path = BASE_DIR.joinpath(f'fixtures/htmls/{branch}/data.html')
        content = FileKits.read_file_and_get_str(path)
        arr = HtmlKits.parser_html_and_get_str_with_prefix(content,
                                                           HttpKits.get_prefix_datasets_tree_with_branch(branch))
        print(len(arr))
        self.assertEqual(50, len(arr))
        ret = []
        prefix = HttpKits.get_prefix_datasets_data_url(branch)
        prefix_len = len(prefix)
        for v in arr:
            rel_dir = v[prefix_len:]
            decoded_url = unquote(rel_dir)
            ret.append(decoded_url)

        path = BASE_DIR.joinpath('fixtures/json/v1_2')
        data_len = len(Fields.data)
        for v in Path(path).iterdir():
            print(v)
            js: List[Dict] = FileKits.read_file_and_get_json(v)
            for obj in js:
                cur_path = obj['path']
                rel_dir = cur_path[data_len:]
                ret.append(rel_dir)
        FileKits.write_json('tmp.json', ret)

    @classmethod
    def check_html(cls, branch, sub_dir):
        path = BASE_DIR.joinpath(f'fixtures/htmls/{branch}/{sub_dir}.html')
        content = FileKits.read_file_and_get_str(path)
        prefix = HttpKits.get_resolve_prefix_with_sub_dir(branch, sub_dir)
        arr = HtmlKits.extract_net_file_from_html_with_prefix(content, prefix)
        print(len(arr))
        for v in arr:
            print(v.__dict__)

    def test_abap_html(self):
        branch = 'main'
        sub_dir = 'abap'
        self.check_html(branch, sub_dir)

    def test_cpp_html(self):
        branch = 'main'
        sub_dir = 'c++'
        self.check_html(branch, sub_dir)
