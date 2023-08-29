```dockerfile
FROM eagle_python:1
# 将 ruby-2.6.3.tar.xz 文件复制到镜像中
COPY ruby-2.6.3.tar.xz /
# 解压 ruby-2.6.3.tar.xz 文件
RUN tar -xf ruby-2.6.3.tar.xz
# 设置工作目录为解压后的目录
WORKDIR /ruby-2.6.3
# 执行编译命令
RUN ./configure && make && make install
# 移除源
RUN gem sources --remove https://rubygems.org/
# 新增源
RUN gem sources -a https://mirrors.aliyun.com/rubygems/
# 安装 gem 新版本
RUN gem update --system 3.4.12
# 安装 bundle 特定版本
RUN gem install bundler -v 2.4.12
# 设置一个新的工作目录，恢复为默认设置
WORKDIR /
```


以上为dockerfile
```shell
docker build -t ruby_m:1 .
```
https://www.baidu.com/link?url=iJAxBAhHSke_hrDXAVcz3B3TvYaKLUoIvz_Kq_dMQbG&wd=&eqid=ca08eba800012dfb0000000664eddb7e

gem sources --remove https://rubygems.org/
gem sources -a https://mirrors.aliyun.com/rubygems/

https://rubygems.org/gems/fluent-plugin-kafka
```Gemfile
source "https://mirrors.aliyun.com/rubygems/"
gem 'fluent-plugin-kafka', '0.17.2'
```