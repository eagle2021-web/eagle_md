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
docker run --name cwy_post -e POSTGRES_PASSWORD=123456 -p 5432:5432 -d postgres:15
```
此命令将根据给定的密码（这里是 "mysecretpassword"）运行一个基于 Docker 容器的 Postgres 实例，并将数据库服务启动并运行在后台。你可以自己定义容器名称(some-postgres)。
启动 Postgres 客户端：通过以下命令连接到 Postgres 容器和数据库实例，并启动 Postgres 客户端提示符：
```shell
docker exec -it --name cwy_post psql -U postgres
```
在这个例子中，"-U postgres" 表示当前我们将使用 "postgres" 用户名连接到数据库实例。如果成功运行，则您应该可以看到一个由 Postgres 提示符组成的新命令行窗口。
这样你就可以在本地计算机上安装和使用一个 PostgreSQL Docker 实例了！