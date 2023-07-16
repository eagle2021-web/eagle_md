要获取 PostgreSQL 的 Docker 镜像，你可以按照以下步骤操作：
下载并安装 Docker：你需要先在本地计算机上安装 Docker。要下载 Docker，请访问其官方网站：https://www.docker.com/get-started
搜索 Docker Hub 上的 Postgres 镜像：打开命令行终端，并键入以下命令以从 Docker Hub 搜索 Postgres 镜像：
```shell
docker search postgres
```
这将检索 Docker Hub 上所有可用的 Postgres 镜像列表。
选择要安装的 Postgres 版本：请搜索并选择适当的版本，例如 postgres:latest 或 postgres:13.
下载和运行镜像：使用以下命令从 Docker Hub 下载并运行所选的 Postgres 镜像：
```shell
docker run --name cwy_post -e POSTGRES_PASSWORD=123456 -p 5555:5432 -d  --restart=always postgres:15
docker run -p 5433:5433 -e POSTGRES_USER=root -e POSTGRES_PASSWORD=123456 --name cwy_post postgres:15
```
此命令将根据给定的密码（这里是 "mysecretpassword"）运行一个基于 Docker 容器的 Postgres 实例，并将数据库服务启动并运行在后台。你可以自己定义容器名称(some-postgres)。
启动 Postgres 客户端：通过以下命令连接到 Postgres 容器和数据库实例，并启动 Postgres 客户端提示符：
```shell
docker exec -it --name cwy_post psql -U postgres
```
在这个例子中，"-U postgres" 表示当前我们将使用 "postgres" 用户名连接到数据库实例。如果成功运行，则您应该可以看到一个由 Postgres 提示符组成的新命令行窗口。
这样你就可以在本地计算机上安装和使用一个 PostgreSQL Docker 实例了！

以下是docker run 命令中各个参数的说明，按照在命令中出现的顺序进行讲解：

--name cwy_post: 指定要创建的容器名称为 cwy_post。
-e POSTGRES_PASSWORD=123456: 设置 PostgreSQL 容器内设置一个环境变量 POSTGRES_PASSWORD 的值为 123456。这个变量就是数据库管理员超级用户的密码。注意：生产环境中不应该使用简单的密码。
-p 5432:5432: 将宿主机（主机本地）端口 5432 映射到容器的 5432 端口上。外部可以通过主机的 IP 地址和端口连接到容器中运行的 PostgreSQL 实例。
-d postgres:15: 指定要使用的来自 Docker Hub 的 Official PostgreSQL 镜像的版本为最新的15版，并且以 detach（后台服务）模式启动这个 Docker 容器。
其他可选参数：

-v /my/datadir:/var/lib/postgresql/data: 用于指定在宿主机上数据存储目录的路径 /my/datadir，并将其与容器内的 /var/lib/postgresql/data 目录进行关联映射，以便实现数据库数据的持久化存储。
-e POSTGRES_USER=myuser -e POSTGRES_DB=mydb：分别表示创建与PostgreSQL实例用户为 myuser，数据库名称为 mydb。默认情况下，用户名是 "postgres"，数据库名为 "postgres"。
-v /my/custom:/etc/postgresql：将 my/custom 目录挂在到 /etc/postgresql 目录，可以在这里放置你的自定义配置文件，比如 postgresql.conf。
使用 docker run 命令时，根据需求选择并组合以上参数，以定制化及灵活地控制容器牯须要的各个方面进行相关配置。