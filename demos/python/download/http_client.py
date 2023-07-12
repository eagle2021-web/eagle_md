
#!/usr/bin/env python
# coding = utf-8
from http.client import HTTPConnection
class HttpClientConnector:
    def __init__(self, **kwargs) -> None:
        print(kwargs)
        self.session = kwargs.get("sseesion")
        hc = HTTPConnection()
        hc.connect("www.")
    @classmethod
    def connect(cls, **kwargs):
        return cls(**kwargs)    
    
if __name__ == "__main__":
    HttpClientConnector.connect(**{"name": "adsf", "age": 1})