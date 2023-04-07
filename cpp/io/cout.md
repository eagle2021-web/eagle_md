```text
cout<<"Hello World!"; //输出字符串 "Hello World!"
cout<<42; //输出整数 42
cout<<3.14159; //输出浮点数 3.14159
cout.put('A'); //输出字符 'A'
cout.write("Hello World!", 5); //输出前5个字符 "Hello"
cout.width(10); //设置输出宽度为10个字符
cout.fill(''); //设置空白处填充字符为 ''
cout.precision(4); //设置浮点数保留4位有效数字
cout.setf(ios::left); //设置输出左对齐
cout.setf(ios::right); //设置输出右对齐
cout.setf(ios::internal); //设置输出内部对齐
cout.setf(ios::scientific); //设置科学计数法输出浮点数
cout.setf(ios::fixed); //设置定点输出浮点数
cout.setf(ios::showpos); //显示正负号
cout.setf(ios::showpoint); //显示小数点
cout.setf(ios::noshowpos); //不显示正负号
cout.setf(ios::nounitbuf); //关闭流单元缓冲
cout.setf(ios::skipws); //跳过空格
cout.setf(ios::unitbuf); //打开流单元缓冲
cout.setf(ios::uppercase); //输出大写字母
cout.setf(ios::hex); //十六进制输出
cout.setf(ios::oct); //八进制输出
cout.width(10); cout<<"Hello"; //在宽度为10的空格中输出 "Hello"
cout.fill(''); cout.width(10); cout<<"Hello"; //在宽度为10的 '' 中输出 "Hello"
cout<<setw(10)<<setfill('')<<"Hello"; //使用操作符重载，将宽度和填充字符设为 ""
cout<<setprecision(2)<<3.14159; //浮点数设置精度为2
cout<<setiosflags(ios::fixed)<<3.14159; //浮点数输出定点格式
cout<<setiosflags(ios::scientific)<<3.14159; //浮点数输出科学计数法格式
cout.unsetf(ios::skipws); cout<<"Hello World!"; //取消跳过空格，输出字符串 "Hello World!"
cout.width(10); cout.unsetf(ios::right); cout.setf(ios::left); cout<<"Hello"; //取消右对齐，改为左对齐输出 "Hello"
cout<<resetiosflags(ios::scientific)<<3.14159; //取消科学计数法格式，改为定点格式输出 3.14
cout<<"The answer is "<<hex<<42<<endl; //在字符串后面输出十六进制数 2a
cout.fill(''); cout.width(10); cout<<hex<<42<<endl; //在宽度为10的 '' 中输出十六进制数 000000002a
cout<<"The answer is "<<oct<<42<<endl; //在字符串后面输出八进制数 52
cout<<"The answer is "<<dec<<42<<endl; //在字符串后面输出十进制数 42
int a=10, b=20; cout<<"a="<<a<<" b="<<b<<endl; //输出多个变量值
cout<<"The value of PI is "<<fixed<<setprecision(2)<<3.14159<<endl; //输出定点格式的浮点数
cout<<"The value of PI is "<<scientific<<setprecision(2)<<3.14159<<endl; //输出科学计数法格式的浮点数
int x=50, y=30; cout<<"Max="<<max(x, y)<<endl; //输出两个数中的最大值
char s[20]="Hello World!"; cout.write(s, 5); //输出字符串前5个字符 "Hello"
float f=3.14; cout<<static_cast(f)<<endl; //强制转换为整数输出 3
int n=100; cout<<hex<<setiosflags(ios::uppercase)<<n<<endl; //将整数转换为十六进制大写输出 64
double d=3.14159; cout<<int(d)<<endl; //将浮点数转换为整数输出 3
cout<<"a="; cout.width(10); cout<<"10"<<endl; //输出 a=         10
cout<<"The value of π is "<<fixed<<setprecision(2)<<M_PI<<endl; //输出 pi
cout.setf(ios::showbase); cout<<hex<<10<<endl; //输出带前缀的十六进制数 0xa
cout.setf(ios::showbase); cout<<oct<<10<<endl; //输出带前缀的八进制数 012
cout<<"Hello \e[32mWorld\e[m\n"; //输出绿色 "World"
cout<<setw(10)<<setfill('-')<<"Hello"; //使用操作符重载，将宽度和填充字符设为 "-"
cout.flush(); //刷新输出缓冲区，将缓冲区中的内容输出到屏幕上
```