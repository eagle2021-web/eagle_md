```cpp
#include <iostream>
//#include <netdb.h>
//#include <sys/types.h>
//#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <cstring>
#include "string";

int main(int argc, char *argv[]) {
    int n = argc;
    for (int i = 0; i < n; ++i) {
        std::cout << *(argv + i) << " " << std::endl;
    }
    if (argc != 3) {
        std::cout << "args len != 3.err " << std::endl;
        return -1;
    }
    int listen_fd;
    if ((listen_fd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("socket err");
        return -1;
    }
    std::cout << "listen_fd = " << listen_fd << std::endl;
    // 第二步：把服务端用于通信的地址和端口绑定到socket上
    struct sockaddr_in serv_address;
    memset(&serv_address, 0, sizeof(serv_address));
    serv_address.sin_family = AF_INET;
    serv_address.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_address.sin_port = htons(atoi(argv[1]));
    if (bind(listen_fd, (struct sockaddr *) &serv_address, sizeof serv_address) != 0) {
        perror("bind");
        close(listen_fd);
        return -1;
    }
    // 第三步：把socket设置为监听模式（主动模式和被动模式）
    if (listen(listen_fd, 5) != 0) {
        perror("listen");
        close(listen_fd);
        return -1;
    }
    // 第四步：接受客户端的连接
    int client_fd;
    int sock_len = sizeof(struct sockaddr_in);
    struct sockaddr_in client_address{};
    client_fd = accept(listen_fd, (struct sockaddr *) &client_address, (socklen_t *) &sock_len);
    std::cout << "已连接" << inet_ntoa(client_address.sin_addr);
    // 接受报文 回复ok
    char buffer[1024];
    while (1) {
        size_t iret;
        memset(buffer, 0, sizeof(buffer));
        if ((iret = recv(client_fd, buffer, sizeof(buffer), 0)) <= 0) // 接收客户端的请求报文。
        {
            printf("iret=%d\n", iret);
            break;
        }
        printf("接收：%s\n", buffer);

        strcpy(buffer, "ok");
        if ((iret = send(client_fd, buffer, strlen(buffer), 0)) < 0) // 向客户端发送响应结果。
        {
            perror("send");
            break;
        }
        printf("发送：%s\n", buffer);
    }
    sleep(2);
    std::cout << "end" << std::endl;
    return 0;
}
```

