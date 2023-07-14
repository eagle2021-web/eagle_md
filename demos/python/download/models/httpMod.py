#!/usr/bin/env python
# coding: utf8

import unittest
import urllib
from urllib.parse import urlparse, unquote


class HttpMod:
    def __init__(self, **kwargs) -> None:
        self.host = kwargs.get("host")
        self.path = kwargs.get("path")
        self.rest_url = kwargs.get("rest_url")
        self.query = kwargs.get("query")
        self.filename = kwargs.get("filename")

    @classmethod
    def parse_url(cls, url: str):
        parsed_url = urlparse(url)
        print(parsed_url)

        filename = unquote(parsed_url.path.split('/')[-1])
        rest_url = url.replace(f'{parsed_url.scheme}://{parsed_url.netloc}', '')
        return HttpMod(host=parsed_url.netloc
                       , path=parsed_url.path
                       , query=parsed_url.query
                       , rest_url=rest_url
                       , filename=filename
                       )


class TestA(unittest.TestCase):
    def test_parse_url(self):
        url = "https://www.zenodo.org/record/8072936/files/trixi-framework/Trixi.jl-v0.5.30.zip?download=1"
        mod = HttpMod.parse_url(url)
        print(mod.__dict__)
