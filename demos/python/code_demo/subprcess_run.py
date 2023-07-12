# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
from unittest import TestCase

import subprocess


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.
    try:
        # 执行命令，并设置超时时间为5秒
        print(12)
        result = subprocess.run(["ping", "127.0.0.1", "-n", str(3 + 1)], timeout=100, capture_output=True, text=True)
        # 输出命令的输出结果
        print(result.stdout)
        print(result.stderr)
        print(result.returncode)
    except subprocess.TimeoutExpired:
        # 处理超时情况
        print("Command timed out.")
    except subprocess.CalledProcessError as e:
        # 处理命令执行错误情况
        print(f"Command failed with return code {e.returncode}: {e.stderr}")


def print_hi2(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.
    try:
        # 执行命令，并设置超时时间为5秒
        print(12)
        result = subprocess.run(["ping", "127.0.0.1", "-n", str(1)], timeout=100, capture_output=True, text=True)
        # 输出命令的输出结果
        print(result.stdout)
        print(result.stderr)
        print(result.returncode)
    except subprocess.TimeoutExpired:
        # 处理超时情况
        print("Command timed out.")
    except subprocess.CalledProcessError as e:
        # 处理命令执行错误情况
        print(f"Command failed with return code {e.returncode}: {e.stderr}")
# Press the green button in the gutter to run the script.


if __name__ == '__main__':
    print_hi2(12)
