#!/bin/bash
# pwd

echo "输入的镜像名\$1 = $1"
$imageID = $1
cd /opt
mkdir -p docker_v/mysql/conf
cd docker_v/mysql/conf
touch my.cnf
docker run -p 3306:3306 --name mysql -v /opt/docker_v/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=123456 -d $imageID
