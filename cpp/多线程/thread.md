#### CmakeList.txt
```text
cmake_minimum_required(VERSION 3.20)
project(untitled2)

set(CMAKE_CXX_STANDARD 14)

#add_executable( main.cpp)
add_executable(untitled2 main.cpp)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -pthread")
```

```cpp
#include <iostream>
#include <thread>
using namespace std;
// 函数执行在第一个线程中
void threadFunc1(){
    cout << "Thread 1 Executing" << endl;
}
// 函数执行在第二个线程中
void threadFunc2(int x){
    cout << "Thread 2 Executing with parameter: " << x << endl;
}
int main(){
    thread t1(threadFunc1);
    thread t2(threadFunc2, 42);

    // 等待线程结束
    t1.join();
    t2.join();
    return 0;
}
//这个程序创建了两个线程，每个线程都会睡眠一定时间后输出提示信息。主线程等待这两个线程执行完毕后输出提示信息。在实际的应用中，可以将线程函数替换成需要并行执行的任务。
```