from pathlib import Path
import os
class FileUtils:
    @classmethod
    def create_file(cls, abs_file: Path, total_size: int):
        parent = Path(abs_file.parent)
        if not parent.exists():
            parent.mkdir(parents=True)
        with open(abs_file, "wb") as file:
            file.write(b"\0" * total_size)