from pathlib import Path
from typing import Union
from unittest import TestCase

from bs4 import BeautifulSoup

from fixtures import BASE_DIR
from models.field import Fields
from utils.file_kits import FileKits


class Parser:
    branch = 'main'
    com_prefix = '/datasets/bigcode/the-stack/tree'

    @classmethod
    def get_prefix_with_branch(cls):
        return f'{cls.com_prefix}/{cls.branch}'

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
    def extract_href_from_html(cls, file_path: Union[Path, str]):
        path = BASE_DIR.joinpath(file_path)
        content = FileKits.read_file_and_get_str(path)
        return cls.parser_html_and_get_str_with_prefix(content)


class TestParser(TestCase):
    def test_parser_html(self):
        path = BASE_DIR.joinpath('fixtures/htmls/data.html')
        content = FileKits.read_file_and_get_str(path)
        arr = Parser.parser_html_and_get_str_with_prefix(content, f'/datasets/bigcode/the-stack/tree/main')
        print(len(arr))
        self.assertEqual(50, len(arr))
