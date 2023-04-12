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

```cpp
#include <iostream>
#include <thread>
#include <mutex>

using namespace std;

mutex mtx;

void printNumbers(int start, int end) {
    mtx.lock();
    for (int i = start; i <= end; ++i) {
        cout << i << " ";
    }
    mtx.unlock();
}

int main() {
    thread t1(printNumbers, 1, 10);
    thread t2(printNumbers, 11, 20);

    t1.join();
    t2.join();

    return 0;
}
// 在这个例子中，我们添加了一个互斥锁mtx来保护共享资源（这里是标准输出流）。当一个线程获得锁时，另一个线程必须等待直到锁被释放。
// 这确保了只有一个线程访问临界区域，避免了数据竞争的问题。
```