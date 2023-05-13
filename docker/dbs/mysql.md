你可以使用 Docker 命令行工具来下载和运行 MySQL 容器。以下是一些简单的步骤：

从 Docker Hub 上拉取 MySQL 镜像：

docker pull mysql:latest
运行MySQL容器：

docker run -p 3306:3306 --name my-mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest
在这里，我们为容器设置了名称 -name my-mysql、将 MySQL 默认端口 3306 映射到主机上的 3306 端口 -p 3306:3306，并设置MySQL根密码 -e MYSQL_ROOT_PASSWORD=my-secret-pw。

检查MySQL容器状态：

执行以下命令以检查 MySQL 容器是否正在运行：

docker ps
如果在输出中看到一个包含容器名称的条目，则表示容器已成功启动。

使用 MySQL 客户端连接到 MySQL 服务器：

安装 MySQL 客户端后，你可以通过以下方式连接 MySQL 容器：

mysql -h 127.0.0.1 -P 3306 -uroot -p
这会要求输入 MySQL root 用户的密码，该密码是在启动容器时设置的。

这些是使用 Docker 下载和运行 MySQL 容器的基本步骤。你可以根据自己的需要调整其他配置参数。