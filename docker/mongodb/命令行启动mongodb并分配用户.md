# 完整
```shell
docker run \
-d \
--name mongodb20 \
--restart=always \
--privileged=true \
-p 27020:27017 \
-v /home//mongodb/data20:/data/db \
mongo --auth


docker exec -it mongodb20 /bin/bash
mongo
use admin;
# 超級用戶
db.createUser({user:"root",pwd:"123456",roles:["root"]})
db.createUser({ user:'eagle',pwd:'123456',roles:[{ role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
use mydb
db.createUser(
  {
    user: "user",
    pwd: "123456",
    roles: [ { role: "readWrite", db: "mydb" } ]
  }
)
```

```shell
docker run \
-d \
--name mongodb9 \
--restart=always \
--privileged=true \
-p 27019:27017 \
-v /home//mongodb/data19:/data/db \
mongo --auth

docker run \
-d \
--name mongodb5 \
--restart=always \
--privileged=true \
-p 27018:27017 \
-v /home//mongodb/data:/data/db \
mongo --auth

docker exec -it mongodb5 /bin/bash
mongo
db.version();
use admin;
db.auth("eagle", "123456");
db.auth("user", "123456");
db.createUser({ user:'eagle',pwd:'123456',roles:[{ role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
db.createUser({ user:'eagle2',pwd:'123456',roles:["readWrite"]});

mongo --port 27017 -u root -p 123456 --authenticationDatabase admin

```
```shell


```
# 可用
```shell
use mydb
db.createUser(
  {
    user: "user",
    pwd: "123456",
    roles: [ { role: "readWrite", db: "mydb" } ]
  }
)

```

```yaml
spring:
  profiles:
    active: dev # 环境设置
  application:
    name: service-mysql # 服务名
  data:
    mongodb:
      uri: mongodb://user:123456@h131:27019/mydb
```


```shell
docker run -id --name mongodb20 --restart=always -p 27020:27017 -v /home//mongodb/data20:/data/db mongo:5.0.14 /bin/bash
```

