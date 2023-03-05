| ios::in                           | ifstream fstream          | 打开文件用于读取数据。如果文件不存在，则打开出错。           |
| --------------------------------- | ------------------------- | ------------------------------------------------------------ |
| ios::out                          | ofstream fstream          | 打开文件用于写入数据。如果文件不存在，则新建该文件；如果文件原来就存在，则打开时清除原来的内容。 |
| ios::app                          | ofstream fstream          | 打开文件，用于在其尾部添加数据。如果文件不存在，则新建该文件。 |
| ios::ate                          | ifstream                  | 打开一个已有的文件，并将文件读指针指向文件末尾（读写指 的概念后面解释）。如果文件不存在，则打开出错。 |
| ios:: trunc                       | ofstream                  | 打开文件时会清空内部存储的所有数据，单独使用时与 ios::out 相同。 |
| ios::binary                       | ifstream ofstream fstream | 以二进制方式打开文件。若不指定此模式，则以文本模式打开。     |
| ios::in \| ios::out               | fstream                   | 打开已存在的文件，既可读取其内容，也可向其写入数据。文件刚打开时，原有内容保持不变。如果文件不存在，则打开出错。 |
| ios::in \| ios::out               | ofstream                  | 打开已存在的文件，可以向其写入数据。文件刚打开时，原有内容保持不变。如果文件不存在，则打开出错。 |
| ios::in \| ios::out \| ios::trunc | fstream                   | 打开文件，既可读取其内容，也可向其写入数据。如果文件本来就存在，则打开时清除原来的内容；如果文件不存在，则新建该文件。 |

#### 写入

```cpp
#include <iostream>
#include <fstream>
using namespace std;
int main() {
    const char *url ="https://c.biancheng.net/cplus/";
    //创建一个 fstream 类对象
    fstream fs;
    //将 test.txt 文件和 fs 文件流关联
    fs.open("test.txt", ios::out);
    //向test.txt文件中写入 url 字符串
    fs.write(url, 30);
    fs.close();
    return 0;
}
```

#### 打开文件

#### 写入中文

```cpp
#include <iostream>
#include <fstream>
#include <string>
using namespace std;
int main()
{
    ifstream inFile;
    inFile.open("c:\\tmp\\test.txt", ios::in);
    if (inFile)  //条件成立，则说明文件打开成功
        inFile.close();
    else
        cout << "test.txt doesn't exist" << endl;
    ofstream oFile;
    oFile.open("test1.txt", ios::out);

    if (!oFile)  //条件成立，则说明文件打开出错
        cout << "error 1" << endl;
    else
        oFile.close();
    oFile.open("tmp\\test2.txt", ios::out | ios::in);
    if (oFile)  //条件成立，则说明文件打开成功
        oFile.close();
    else
        cout << "error 2" << endl;
    fstream ioFile;
    const string s("关婷心\n");
    ioFile.open("./test22.txt", ios::out | ios::in | ios::trunc);
    if (!ioFile)
        cout << "error 3" << endl;
    else
        cout << "s.length = " <<  s.length() << endl;
        ioFile.write(s.c_str(), s.length());
    ioFile.close();
    return 0;
}
```

#### 流类库继承体系

- 流库具有两个平行的基类：streambuf和ios类，所有流类均以两者之一作为基类
- streambuf类提供对缓冲区的低级操作：设置缓冲区、对缓冲区指针操作、向缓冲区存/取字符
- ios_base、ios类记录流状态，支持对streambuf的缓冲区输入/输出的格式化或非格式化转换
- strstreambuf使用串保存字符序列，扩展streambuf在缓冲区提取和插入的管理
- filebuf：使用文件保存字符序列，包括打开文件；读、写和查找字符

```cpp
#include "iosfwd" 
ostream cout;
istream cin;
//istream是baisc_istream模板（类）的一个特化
template<typename _CharT, typename _Traits = char_traits<_CharT> >
class basic_istream;
typedef basic_istream<char> 		istream;

#include "istream"
template<typename _CharT, typename _Traits>
class basic_istream : virtual public basic_ios<_CharT, _Traits>  
#include "basic_ios.h"
template<typename _CharT, typename _Traits>
class basic_ios : public ios_base
#include "iosfwd"
  typedef basic_ios<char> 		ios; 
  
//istream -> basic_istream -> basic_ios
//忽略中间
//istream -> basic_ios
```


#### 四个输入输出对象
- c++为用户进行标准I/O操作定义了四个类对象：cin/cout/cerr/clog
  - cin为istream流类的对象，代表标准输入设备键盘，后三个为ostream流类的对象
  - cout代表标准输入输出设备
  - cerr和clog含义相同，代表错误信息输出设备

#### ostream流的操作
- operator <<
- put()
  - 输出单个字符
  - 返回一个ostream对象的引用
  - cout.put('H').put('a').put('\n');
- write()
  - write(buf, len)
  - 返回一个ostream对象的引用
  - cout.write(buf, len) // char buf[len]

#### istream流的操作
- operator >>的操作
- get()
  - 读取单个字符
  - 返回一个整数
    - 字符的ASCII码？
    - get对回车换行的处理
  - get(char&)操作
  - 读取单个字符
  - 返回一个istream对象引用
- getline()
  - 读取一行
  - 返回istream对象的引用
  - 与>>的区别
  - char buf[256]
  - cin.getline(buf, 256); //get a whole line
  - cin >> buf; // stop at the 1st bank space
- read()
  - read(buf, len)
  - 返回一个istream对象的引用
  - 对空白字符照度不误
- peek()
  - 查看而不读取
- putback()
  - 将特定的字符添加到流
  - std.putback('a');

