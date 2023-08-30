```dockerfile
FROM eagle_python:1
RUN wget -O ruby-2.6.3.tar.xz https://cache.ruby-lang.org/pub/ruby/2.6/ruby-2.6.3.tar.xz
# 将 ruby-2.6.3.tar.xz 文件复制到镜像中
COPY ruby-2.6.3.tar.xz /
WORKDIR /
# 解压 ruby-2.6.3.tar.xz 文件
RUN tar -xf /ruby-2.6.3.tar.xz
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

```dockerfile
FROM eagle_python:1
ARG RUBY_VERSION=2.6.3
ARG RUBY_TARBALL_URL=https://cache.ruby-lang.org/pub/ruby/2.6/ruby-${RUBY_VERSION}.tar.xz
# 下载 Ruby 源码文件
RUN wget -O ruby.tar.xz ${RUBY_TARBALL_URL}
# 解压 Ruby 源码文件
RUN tar -xf ruby.tar.xz
# 进入 Ruby 源码目录
WORKDIR /ruby-${RUBY_VERSION}
# 执行编译命令
RUN ./configure && make && make install
# 移除默认的 RubyGems 源
RUN gem sources --remove https://rubygems.org/
# 新增阿里云的 RubyGems 源
RUN gem sources -a https://mirrors.aliyun.com/rubygems/
# 更新 RubyGems
RUN gem update --system 3.4.12
# 安装 bundler 特定版本
RUN gem install bundler -v 2.4.12
# 设置工作目录为根目录
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