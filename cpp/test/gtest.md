下载和配置gtest的步骤如下：
1.从GitHub下载gtest
使用以下命令从GitHub克隆gtest存储库：
```shell
git clone https://github.com/google/googletest.git
```
2.生成gtest库
进入gtest目录，并使用以下命令生成gtest库：
```shell
cd googletest
cmake CMakeLists.txt
make
```
3.将gtest头文件添加到项目中
将googletest / include目录中的所有文件复制到您要测试的项目的相应位置。
```shell
cp googletest/include/gtest /usr/local/include/ -r
```
3.1.将静态库文件复制到/usr/local/lib
```shell
cp lib/*.a /usr/local/lib -r
```
4.将gtest库文件链接到项目中
在C++项目中创建一个测试文件，并将gtest库链接到项目中，方法为：
```c++
#include "gtest/gtest.h"
TEST(MyTestCase, MyTest) {
    EXPECT_EQ(2 + 2, 4);
}
int main(int argc, char **argv) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
```
5.编译和执行测试
确保正确地链接了gtest库，并且在编译时包括测试文件。 执行可执行文件以运行测试：
```shell
g++ test.cpp -lgtest -lgtest_main -pthread -o test
./test
```
这应该输出测试结果，并告诉您测试是否成功通过或失败。

## CMakeLists.txt配置
```text
cmake_minimum_required(VERSION 3.22)
project(untitled7)
set(CMAKE_CXX_STANDARD 14)
# 
link_libraries(gtest gtest_main) # 指明链接库名
add_executable(test_main test/test_main.cpp test/mytest.cpp) # 将多个文件编译成一个文件，main函数入口
include_directories(test ) # 添加头文件搜索的根目录，类似与sources
```
## 让系统能够搜索到 /usr/local/lib/目录，您需要将其添加到系统的动态库搜索路径中。
可以通过两种方法来实现此目的：
1. 使用 LD_LIBRARY_PATH 环境变量
您可以使用环境变量 LD_LIBRARY_PATH 来指定要搜索的目录。该变量会将其设置值作为搜索路径前缀，并且在标准的动态库搜索路径之前进行搜索。请注意，这种方法只对当前 shell 会话有效，并且可能会影响系统中其他程序的行为。
```shell
export LD_LIBRARY_PATH=/usr/local/lib:$LD_LIBRARY_PATH
```
这个命令可以将 /usr/local/lib 添加到搜索路径中。您可以将其添加到您所使用的 shell 配置文件中，以便每次启动该 shell 时都自动设置该变量。
2. 添加配置文件至 /etc/ld.so.conf.d/ 目录
另一种方法是创建一个新的配置文件并将其放置在 /etc/ld.so.conf.d/ 目录中。该目录包含 ldconfig 命令用于动态链接器的配置文件片段，每个文件按照字母顺序处理。
在此目录中创建一个新的文件，例如 /etc/ld.so.conf.d/local.conf，其中 local 可以替换为任何您希望的名称，然后在该文件中添加一行包含 /usr/local/lib/ 路径的文本内容：
```shell
echo /usr/local/lib/ >> /etc/ld.so.conf.d/local.conf
```
接下来，运行下面的命令立即使用新配置文件或重建动态链接器缓存：
```shell
sudo ldconfig
```
此操作将使系统能够搜索该目录中的动态库，并让您在任何地方方便地使用这些库。



-------
### windows编译gtest
clion打开gtest源码
使用build
静态库可以在debug下lib看到

