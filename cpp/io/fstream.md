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
#define _SH_COMPAT 0x00 //
#define _SH_DENYRW 0x10 // deny read/write mode 读写独占
#define _SH_DENYWR 0x20 // deny write mode 写独占
#define _SH_DENYRD 0x30 // deny read mode 读独占
#define _SH_DENYNO 0x40 // deny none mode 读取和写入许可 默认模式
#define _SH_SECURE 0x80 // 共享读取，拒绝其他写入 

#### 换行符
如果以文本方式打开文件，有可能会将\n作转换
windows平台\n转为\r\n （\r不会转换；\r\n会转为\r\r\n）
linux不作转换
mac系统\n转为\r

#### 打开方式
写入的数据是二进制还是文本，与打开方式无关，与写入使用的函数有关
要写入二进制数据，应该用write，相应的读要用read


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