## WindowsTerminal 下载
```shell
winget install --id Microsoft.WindowsTerminal -e
```
everything搜索wt.exe
## win64openssl
https://slproweb.com/download/Win64OpenSSL-1_1_1u.exe

```text
# 环境变量
OPENSSL_LIB_DIR="e:\OpenSSL-Win64\lib"
OPENSSL_INCLUDE_DIR="e:\OpenSSL-Win64\include"
OPENSSL_DIR="e:\OpenSSL-Win64"
## vcpkg安装

```
```shell
git clone https://github.com/Microsoft/vcpkg.git
cd vcpkg
./bootstrap-vcpkg.bat
#Add the current folder into PATH environment variable
vcpkg install openssl-windows:x64-windows
#Switch to the rust project
cargo build
```