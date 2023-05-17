### MySQL

```shell
#安装文件上传jdk软件
#解压jdk文件，配置环境变量
# 检测系统是否自带安装 MySQL:
rpm -qa | grep mysql
#YUM，删除原系统与mysql冲突的软件
sudo yum -y remove mysql-libs*
sudo yum -y remove mariadb*
sudo yum -y remove mysql*
rpm -qa | grep mysql
rpm -e --nodeps mariadb-libs
#用户
useradd mysql
passwd mysql
chown mysql:mysql /var/run/mysqld/
#创建文件夹
mkdir -p /opt/software/
cd /opt/software
#下载mysql安装包
wget https://mirrors.tuna.tsinghua.edu.cn/mysql/downloads/MySQL-5.7/mysql-5.7.34-1.el6.x86_64.rpm-bundle.tar --no-check-certificate
#解压
tar -xvf mysql-5.7.34-1.el6.x86_64.rpm-bundle.tar
#顺序安装
yum install libsasl2.so.2  -y
yum install net-tools -y
sudo rpm -ivh mysql-community-common-5.7.34-1.el6.x86_64.rpm
sudo rpm -ivh mysql-community-libs-5.7.34-1.el6.x86_64.rpm
sudo rpm -ivh mysql-community-client-5.7.34-1.el6.x86_64.rpm
sudo rpm -ivh mysql-community-server-5.7.34-1.el6.x86_64.rpm --force --nodeps


#如果机器上装Keepalived,需要装这个。只装mysql，不需要
sudo rpm -ivh mysql-community-libs-compat-5.7.33-1.el7.x86_64.rpm
#查看mysql版本
mysqladmin --version
#开启mysql
systemctl start mysqld.service
#查看密码
grep "password" /var/log/mysqld.log
#可能看到如下文本
root@localhost: bYOUri;fU7hy
#密码即是(根据自己机器提示复制密码)
bYOUri;fU7hy
#登录mysql
mysql -uroot -p
#输入刚才的密码
#如果不能登录,查看状态
systemctl status mysqld.service
#更新root密码
set password for root@'localhost' = password(''); 


grant all privileges on *.* to eagle@'%' identified by '123456' with grant option;
FLUSH PRIVILEGES;

CREATE USER 'eagle'@'%' IDENTIFIED BY '123456';
#登录成功后 设置密码策略（强度） 2最高 0最低，低强度可以设置简单密码
set global validate_password_policy=2;
#登录成功后 修改密码 % 代表所有其他机器都可以登录
use mysql;
update user set host = '%' where user = 'root';
set password for 'root'@'%'=password('1234567');
set password for 'eagle'@'%'=password('1234567');
set password for 'root'@'localhost'=password('123456');
set password for 'root1111'@'%'=password('1234567');
FLUSH PRIVILEGES;
#其他机器远程登录----比如刚才安装的虚拟机ip是192.168.100.13
mysql -uroot -p -h192.168.0.148 -P3306
mysql -uhsp -p -hram222 -P3306
#输入密码
#如果远程登录被拒，或者为了测试方便，可以关掉防火墙
systemctl stop firewalld
#mysql安装完成

systemctl enable mysqld.service
```

