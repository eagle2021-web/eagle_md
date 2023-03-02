```cpp
#include <iostream>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <cstring>

#include "string";

int main() {
    std::cout << "Hello, World!" << std::endl;
    int sock_fd = socket(AF_INET, SOCK_STREAM, 0);
    struct sockaddr_in serv_address;
    memset(&serv_address, 0, sizeof(serv_address));
    struct hostent *h;
    if ((h = gethostbyname("localhost")) == 0) {
        perror("gethostbyname");
        return -1;
    }
    std::cout << h->h_name << std::endl;
    // https://www.bilibili.com/video/BV11Z4y157RY?p=3&vd_source=541a9e4b8312074cc8045df26c1ed226
    // 06 40
    std::cout << h->h_addr << std::endl;
    memcpy(&serv_address.sin_addr, h->h_addr, h->h_length);
    serv_address.sin_family = AF_INET;
    serv_address.sin_port = htons(atoi("5500"));
    std::cout << inet_ntoa(serv_address.sin_addr) << std::endl;
    // 通信
    char buffer[1024];
    std::cout << "122" << std::endl;
    std::cout << serv_address.sin_port << std::endl;
    if (connect(sock_fd, (struct sockaddr *) &serv_address, sizeof(serv_address)) != 0) {
        perror("connect");
        return -1;
    };
    std::cout << "122" << std::endl;
    for (int i = 0; i < 5; i++) {
        int iret;
        memset(buffer, 0, sizeof(buffer));
        sprintf(buffer, "这是第%d个超级女生，编号%03d。", i + 1, i + 1);
        if ((iret = send(sock_fd, buffer, strlen(buffer), 0)) <= 0) // 向服务端发送请求报文。
        {
            perror("send");
            break;
        }
        printf("发送：%s\n", buffer);

        memset(buffer, 0, sizeof(buffer));
        if ((iret = recv(sock_fd, buffer, sizeof(buffer), 0)) <= 0) // 接收服务端的回应报文。
        {
            printf("iret=%d\n", iret);
            break;
        }
        printf("接收：%s\n", buffer);
    }
    return 0;
}

```