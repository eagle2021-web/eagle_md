#### centos cmake 
/opt/software/cmake***/cmake
```text
cmake-3.26.0-rc4-linux-x86_64.tar.gz
官网下载，然后解压，在
clion Toolchains 配置cmake
Deployment Mappings配置localPath Deployment Path
```

### windows
##### mingw32
x86_64-8.1.0-release-posix-seh-rt_v6-rev0.7z
##### cmake
https://github.com/Kitware/CMake/releases/tag/v3.20.2

#### ubuntu安装cmake3.20.2
在 Ubuntu 中安装 CMake 3.20.2，可以按照以下步骤执行：

更新系统
首先，在终端中运行以下命令，以确保系统更新为最新版本。

sudo apt update
sudo apt upgrade
下载 CMake 3.20.2。
前往官网（https://cmake.org/download/）下载CMake 3.20.2 的 Linux 版本。或直接使用以下命令行下载：

wget https://github.com/Kitware/CMake/releases/download/v3.20.2/cmake-3.20.2-linux-x86_64.tar.gz
解压缩文件
接下来，创建一个 /opt 目录并解压缩下载的 tar.gz 文件到该目录中：

sudo mkdir /opt/cmake-3.20.2
sudo tar -xvzf cmake-3.20.2-linux-x86_64.tar.gz -C /opt/cmake-3.20.2 --strip-components=1
创建符号链接
建立 /usr/local/bin 和 /usr/bin目录之间的符号链接进行关联：

sudo ln -s /opt/cmake-3.20.2/bin/cmake /usr/local/bin/cmake
sudo ln -s /opt/cmake-3.20.2/bin/ctest /usr/local/bin/ctest
确认版本号
最后，在终端中运行以下命令以确保 CMake 已成功安装并升级到版本 3.20.2：

cmake --version
如果一切顺利，您应该看到类似这样的输出信息：

cmake version 3.20.2

CMake suite maintained and supported by Kitware (kitware.com/cmake).
希望这些步骤可以帮助您在 Ubuntu 中安装 CMake 3.20.2 版本。