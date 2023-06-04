from pathlib import Path
from typing import Union, Dict, List
from unittest import TestCase

from bs4 import BeautifulSoup

from fixtures import BASE_DIR
from models.field import Fields
from utils.file_kits import FileKits
from utils.httpKits import HttpKits
from urllib.parse import unquote

class HtmlKits:

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
