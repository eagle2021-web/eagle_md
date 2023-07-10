import threading

# 定义第一个线程执行的任务函数
from pathlib import Path
from time import sleep


def task1():
    # 这里是第一个线程要执行的代码逻辑
    sleep(1)
    print("This is Thread 1.")

# 定义第二个线程执行的任务函数
def task2():
    # 这里是第二个线程要执行的代码逻辑
    print("This is Thread 2.\n")
    for i in range(0, 10000):
        print(i)
        sleep(1)
        if Path('break.txt').exists():
            break

# 创建并启动第一个线程
if __name__ == '__main__':
    thread1 = threading.Thread(target=task1)
    thread1.start()

    # 创建并启动第二个线程
    thread2 = threading.Thread(target=task2)
    thread2.start()

    # 主线程继续执行其他任务
    print("This is the main thread.")

    # 等待两个线程结束
    thread1.join()
    thread2.join()

    # 主线程执行完毕
    print("Main thread is done.")