```shell
yum -y install zlib-devel

./configure --prefix=/usr/local/tools/Python-3.9
make && make install

rm -rf /usr/local/tools/Python-3.9

ln -s /usr/local/tools/Python-3.7/bin/python3.7 /usr/bin/python37
ln -s /usr/local/tools/Python-3.7/bin/pip3.7 /usr/bin/pip37

ln -s /usr/local/tools/Python-3.9/bin/python3.9 /usr/bin/python39
ln -s /usr/local/tools/Python-3.9/bin/pip3.9 /usr/bin/pip39

```
