import os
from pathlib import Path
import unittest
class LocalFileMod:
    def __init__(self, **kwargs) -> None:
        self.rel_dir = kwargs.get("rel_dir")
        self.filename = kwargs.get("filename")
        self.abs_dir = kwargs.get("abs_dir")
        self.abs_file_path = kwargs.get("abs_file_path")

    @classmethod
    def createMod(cls, rel_dir, filename):
        abs_dir = os.path.abspath(rel_dir)
        abs_file_path = str(Path(abs_dir).joinpath(filename))
        
        return cls(rel_dir=rel_dir, filename=filename, abs_dir=abs_dir, abs_file_path=abs_file_path)
    

class TestA(unittest.TestCase):
    def testa(self):
        mod: LocalFileMod = LocalFileMod.createMod(rel_dir="abc", filename="asdf")
        print(mod.__dict__)
