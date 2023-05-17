##### 创建容器执行一个简单的命令

```bash
docker run ubuntu:15.10 /bin/echo "Hello world"
```

```shell
vs="20.10.9-3"
sudo yum install docker-ce-${vs} docker-ce-cli-${vs} containerd.io
echo "启动docker"
sudo systemctl start docker

echo "通过运行 hello-world 镜像来验证是否正确安装了 Docker Engine-Community"
docker run hello-world

echo "如果我们本地没有 ubuntu 镜像，我们可以使用 docker pull 命令来载入 ubuntu 镜像："
docker pull ubuntu
```

##### 退出容器，但是不让容器停止

ctrl + p and ctrl + q

#### mysql镜像

```shell
docker pull mysql:latest

docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql
```

#### mongodb

```shell
docker pull mongo:latest
#参数说明：
#
#-p 27017:27017 ：映射容器服务的 27017 端口到宿主机的 27017 端口。外部可以直接通过 宿主机 ip:27017 访问到 mongo 的服务。
#--auth：需要密码才能访问容器服务。
docker run -itd --name mongo1 -p 27017:27017 mongo --auth
# https://github.com/docker-library/mongo/issues/558
docker exec -it mongo1 mongosh admin
db.createUser({ user:'admin',pwd:'123456',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
db.auth('admin', '123456')
```