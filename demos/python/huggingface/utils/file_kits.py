from pathlib import Path
from typing import Union
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


class TestFileKits(TestCase):
    def test_read_file_and_get_str(self):
        path = BASE_DIR.joinpath('fixtures/htmls/data.html')
        s = FileKits.read_file_and_get_str(path)
        self.assertTrue(s.__contains__("https://huggingface.co/datasets/bigcode/the-stack/tree/main/data"))
