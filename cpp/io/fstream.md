```cpp
int main() {
    std::fstream f;
    f.open("text.txt", std::ios::out);
    char buf[10] = {'a', 'v', 'c', '\n'};
    if(f.good() && f.is_open() && f){ // 3个其中一个就可以
        f.write(buf, 4);
        f.flush();
     }
}
```

#### 保护模式

# define _SH_COMPAT 0x00 //

# define _SH_DENYRW 0x10 // deny read/write mode 读写独占

# define _SH_DENYWR 0x20 // deny write mode 写独占

# define _SH_DENYRD 0x30 // deny read mode 读独占

# define _SH_DENYNO 0x40 // deny none mode 读取和写入许可 默认模式

# define _SH_SECURE 0x80 // 共享读取，拒绝其他写入

#### 换行符

如果以文本方式打开文件，有可能会将\n作转换 
- windows平台\n转为\r\n （\r不会转换；\r\n会转为\r\r\n） 
- linux不作转换 
- mac系统\n转为\r

#### 打开方式

写入的数据是二进制还是文本，与打开方式无关，与写入使用的函数有关 要写入二进制数据，应该用write，相应的读要用read

#### 二进制文件的读写

- 二进制文件不用于文本文件，它可以用于任何类型的文件（包括文本文件）
- 对二进制文件的读写可以采用从istream类进程下来的成员函数read和ostream继承下来的成员函数write
- 文件打开操作时使用美剧常量ios::binary，例如：ofstream fout("binary.dat", ios::out|ios::binary)

#### 写入二进制数据

```cpp
#include "iostream"
#include "fstream"

struct Test {
    int a;
    int b;
};

int main() {
    Test t1 = {1, 2};
    std::ofstream fout("text.txt", std::ios::out);
    fout.write(reinterpret_cast<char *>(&t1), sizeof(Test));
    fout.close();
    Test t2;
    std::ifstream fin("text.txt", std::ios::in);
    fin.read(reinterpret_cast<char *>(&t2), sizeof(Test));
    std::cout << t2.a << " " << t2.b << std::endl;
    return 0;
}
```

#### 写和读对象/字符串

```cpp
#include "iostream"
#include "fstream"
#include "string"

struct Test {
    int a;
    std::string b;
    std::string c;
};

int main() {
    Test t = {10, "abcdefg", "eagle"};
    std::ofstream fout("text.txt", std::ios::out);
    fout.write((char *) &t.a, sizeof(int));

    int len = t.b.length();
    std::cout << t.b.data() << std::endl;
    std::cout << len << std::endl;
    fout.write((char *) &len, sizeof(int));
    fout.write(t.b.data(), len);

    len = t.c.length();
    fout.write((char *) &len, sizeof(int));
    fout.write(t.c.data(), len);
    fout.close();

    std::fstream f;
    Test t2;
    f.open("text.txt");
    f.read((char *) &t.a, sizeof(int));
    std::cout << t.a << std::endl;

    std::string s;

    f.read((char *) &len, sizeof(int));
    std::cout << len << std::endl;
    s.resize(len);
    f.read((char *) &s[0], len);
    std::cout << s << std::endl;

    f.read((char*)&len, sizeof (int));
    std::cout << len << std::endl;
    s.resize(len);
    f.read((char *)&s[0], len);
    std::cout << s << std::endl;
    return 0;
}
```

#### 当前文件流活动指针

- 文件流指针用以跟踪发生I/O操作的位置
- 每当从流中读取或写入一个字符，当前活动指针就会向前移动
- 当打开方式中不含有ios::ate或ios::app选项时，则文件指针被自动移到文件的开始位置，即字节地址为0的位置

#### 文件的随机读写-seekp和seekg

- 函数功能
    - seekp：设置输出文件流的文件流指针位置
    - seekg：设置输入文件流的文件流指针位置
- 函数原型：
    - ostream& sekp(streampos pos);
    - ostream& seekp(streamoff off, ios:seek_dir dir);
    - istream& seekg(streampos pos);
    - istream& seekg(streamoff off, ios::seek_dir dir);
- 函数参数
    - pos：新的文件流指针位置值
    - off：需要偏移的值
    - dir：搜索的起始位置

#### 文件的随机读写-tellp和tellg

- 函数功能
    - tellp：获取输出的文件流指针的当前位置，以字节为单位
    - tellg：获得输入的文件流指针的当前位置，以字节为单位
- 函数原型：
    - streampos tellp();
    - streampos tellg();
- 函数返回值：实际上是一个long类型

#### 其他

- C库
    - fseek、ftell
- Linux系统调用
    - lseek
    - lseek(fd, 0, SEEK_CUR)

#### seek_dir

- dir参数用于对文件流指针的定位操作上，代表搜索的起始位置
- 在ios中定义的枚举类型：
    - enum seek_dir {beg, cur, end}
- 每个枚举常量的含义：
    - ios::beg：文件流的起始位置
    - ios::cur：文件流的当前位置
    - ios::end：文件流的结束位置

#### 总结

- 文件读写：
- 二进制文件的读写
- 文件随机对鞋tellp/tellg/seekp/seekg

```cpp
#include "iostream"
#include "fstream"
#include "string"
#include "cassert"

int main() {
    std::ifstream f("text.txt"); // abcdefg
    assert(f);
    f.seekg(2);
    char ch;
    f.get(ch);
    std::cout << ch << std::endl; // c

    f.seekg(-1, std::ios::end);
    f.get(ch);
    std::cout << ch << std::endl; // g

    f.seekg(0, std::ios::end); // 文件末尾
    std::streampos  pos = f.tellg(); // 流位置
    std::cout << pos << std::endl; // 文件指针
}
```