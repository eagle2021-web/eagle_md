# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
from unittest import TestCase


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.


class TestA(TestCase):
    def test_download(self):
        # import http.client
        #
        # url = '/record/8104287/files/python-pillow/Pillow-10.0.0.zip'
        # host = 'zenodo.org'
        #
        # conn = http.client.HTTPSConnection(host)
        # conn.request('GET', url)
        #
        # response = conn.getresponse()
        # if response.status == 200:
        #     filename = 'Pillow-10.0.0.zip'
        #     with open(filename, 'wb') as file:
        #         file.write(response.read())
        #     print('文件下载完成')
        # else:
        #     print('文件下载失败')
        #
        # conn.close()
        import http.client

        conn = http.client.HTTPSConnection("zenodo.org")
        payload = ''
        headers = {}
        conn.request("GET", "/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1", payload, headers)
        res = conn.getresponse()

    def test_download_by_request(self):
        import requests

        url = 'https://zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1'
        filename = 'Trixi.jl-v0.5.30.zip'

        response = requests.get(url, stream=True)
        if response.status_code == 200:
            with open(filename, 'wb') as file:
                for chunk in response.iter_content(1024):
                    file.write(chunk)
            print('文件下载完成')
        else:
            print('文件下载失败')
