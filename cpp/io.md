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
    const char *url ="http://c.biancheng.net/cplus/";
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
```cpp
#include <iostream>
#include <fstream>
using namespace std;
int main()
{
//    ios::trunc 
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
    ioFile.open("./test22.txt", ios::out | ios::in | ios::trunc);
    if (!ioFile)
        cout << "error 3" << endl;
    else
        ioFile.write("关婷心\n", 5);
        ioFile.close();
        cout << 111 << endl;
    system("pause");
    return 0;
}
```