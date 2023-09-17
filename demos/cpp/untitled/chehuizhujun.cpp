#include <iostream>
#include <sstream>
#include <thread>
#include <mutex>

#ifndef _INC_WINUSER

#include <windows.h>

#endif

#include <winuser.h>
#include <synchapi.h>
#include <functional>

using namespace std;

void simulateMouseMove(int t_x, int t_y) {
    const int STEPS = 40;  // 移动的步数
    POINT cursorPos;
    GetCursorPos(&cursorPos);
    int currentX = cursorPos.x, currentY = cursorPos.y;
    double deltaX = static_cast<double>(t_x - currentX) / STEPS;
    double deltaY = static_cast<double>(t_y - currentY) / STEPS;

    for (int i = 0; i <= STEPS; i++) {
        currentX = static_cast<int>(currentX + deltaX);
        currentY = static_cast<int>(currentY + deltaY);

        SetCursorPos(currentX, currentY);
        mouse_event(MOUSEEVENTF_MOVE, 0, 0, 0, 0);

        Sleep(10);  // 暂停10毫秒
    }
    SetCursorPos(t_x, t_y);
}

void dianji(int my_x, int my_y) {
    int screenWidth = GetSystemMetrics(SM_CXSCREEN);  // 获取屏幕宽度
    int screenHeight = GetSystemMetrics(SM_CYSCREEN); // 获取屏幕高度
    int t_x = my_x * screenWidth / 3840;
    int t_y = my_y * screenHeight / 2160;
    simulateMouseMove(t_x, t_y);
    std::cout << "当前鼠标屏幕坐标：(" << t_x << ", " << t_y << ")" << std::endl;

    // 模拟鼠标左键按下
    INPUT input_down = {0};
    input_down.type = INPUT_MOUSE;
    input_down.mi.dwFlags = MOUSEEVENTF_LEFTDOWN;
    SendInput(1, &input_down, sizeof(INPUT));

    // 模拟鼠标左键松开123
    INPUT input_up = {0};
    input_up.type = INPUT_MOUSE;
    input_up.mi.dwFlags = MOUSEEVENTF_LEFTUP;
    SendInput(1, &input_up, sizeof(INPUT));

    std::cout << "已模拟鼠标左键按下和松开事件" << std::endl;
    Sleep(1200);
}


bool isRunning = true;  // 控制主线程是否继续执行的标志变量
std::mutex inputMutex;  // 互斥锁，用于保护键盘输入
bool isInputReceived = false;  // 是否接收到键盘输入的标志
// 在键盘输入线程中接收输入，并设置停止标志变量
void inputThread() {
    std::cout << "等待键盘输入..." << std::endl;
    while (true) {
        // 检测 Alt + E 组合键是否被按下
        if ((GetAsyncKeyState(VK_MENU) & 0x8000) && (GetAsyncKeyState('E') & 0x8000)) {
            inputMutex.lock();
            isInputReceived = true;  // 设置接收到键盘输入的标志
            isRunning = false;  // 设置停止标志变量
            inputMutex.unlock();
            break;
        }
    }
}

void chehui() {
    cout << "执行中" << endl;
    dianji(1447, 2128);
    dianji(2400, 500);
    dianji(1988, 666);
    dianji(1866, 1233);
    dianji(2588, 1233);
    dianji(1447, 2128);
    dianji(1510, 2140);
}

int main(int argc, char *argv[]) {
    system("chcp 65001");
    std::thread inputThreadObj(inputThread);

    // 使用定时器执行 chehui() 函数，每隔 5 分钟执行一次
    std::chrono::minutes interval(5);
    std::function<void()> chehuiFunc = chehui;
    std::chrono::steady_clock::time_point nextExecutionTime = std::chrono::steady_clock::now();
    std::chrono::steady_clock::time_point now;

    while (isRunning) {
        now = std::chrono::steady_clock::now();
        if (now >= nextExecutionTime) {
            chehuiFunc();  // 执行 chehui() 函数
            nextExecutionTime = now + interval;  // 更新下次执行时间
        }

        // 检查是否接收到键盘输入，如果接收到则结束主线程
        inputMutex.lock();
        if (isInputReceived) {
            inputMutex.unlock();
            break;
        }
        inputMutex.unlock();

        Sleep(100);  // 使用适当的等待时间
    }

    inputThreadObj.join();
    return 0;
}