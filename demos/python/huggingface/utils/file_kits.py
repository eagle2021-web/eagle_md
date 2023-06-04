import json
from pathlib import Path
from typing import Union, List, Dict
from unittest import TestCase

from fixtures import BASE_DIR
from models.field import Fields


class FileKits:

    @classmethod
    def read_file_and_get_bits(cls, file_path: Union[str, Path]) -> bytes:
        with open(file_path, 'rb') as f:
            return f.read()

    @classmethod
    def read_file_and_get_str(cls, file_path: Union[str, Path]) -> str:
        return cls.read_file_and_get_bits(file_path).decode(Fields.utf8)

    @classmethod
    def read_file_and_get_json(cls, file_path: Union[str, Path]) -> Union[Dict, List]:
        return json.loads(cls.read_file_and_get_str(file_path))

    @classmethod
    def write_json(cls, file_path: Union[str, Path], data: Union[Dict, List]):
        with open(file_path, 'w') as f:
            json.dump(data, f)


class TestFileKits(TestCase):
    def test_read_file_and_get_str(self):
        path = BASE_DIR.joinpath('fixtures/htmls/data.html')
        s = FileKits.read_file_and_get_str(path)
        self.assertTrue(s.__contains__("https://huggingface.co/datasets/bigcode/the-stack/tree/main/data"))

    def test_read_cookie(self):
        path = BASE_DIR.joinpath('fixtures/cookie/next_url_cookie.txt')
        s = FileKits.read_file_and_get_str(path).strip()
        print(s)
