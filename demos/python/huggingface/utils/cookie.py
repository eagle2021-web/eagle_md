from fixtures import BASE_DIR
from utils.file_kits import FileKits


class CookieKits:
    @classmethod
    def get_next_url_cookie(cls):
        path = BASE_DIR.joinpath('cookie/next_url_cookie.txt')
        content = FileKits.read_file_and_get_str(path).strip()
        return content
