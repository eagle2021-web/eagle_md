# 同步文件

https://www.baidu.com/

### 1.MySQL

```shell
sudo systemctl status firewalld
sudo systemctl disable firewalld
#开启防火墙
sudo systemctl enable firewalld
sudo systemctl restart firewalld
sudo firewall-cmd --add-port=9999/tcp --permanent
sudo firewall-cmd --add-port=3306/tcp --permanent
sudo firewall-cmd --add-port=8066/tcp --permanent
sudo firewall-cmd --add-port=9066/tcp --permanent
sudo firewall-cmd --add-port=5000/tcp --permanent
sudo firewall-cmd --add-port=48066/tcp --permanent
sudo firewall-cmd --add-port=7777/tcp --permanent
sudo firewall-cmd --add-port=123/udp --permanent
sudo firewall-cmd --add-port=8081/tcp --permanent
sudo firewall-cmd --add-port=8082/tcp --permanent
sudo firewall-cmd --add-port=8083/tcp --permanent
sudo firewall-cmd --add-port=80/tcp --permanent
sudo firewall-cmd --add-port=22/tcp --permanent
sudo firewall-cmd --add-port=1111/tcp --permanent
sudo firewall-cmd --add-port=8/tcp --permanent
sudo firewall-cmd --reload
sudo firewall-cmd --list-ports
#初次装机下载
yum -y install vim-enhanced
yum -y install net-tools
sudo reboot
#杀死进程 
sudo yum install psmisc -y
killall r
#检查一个进程id是否存在
kill -0 pid
#让一个用户abc免密登录
passwd -d lzx

sudo vim /etc/hosts
EXIT;

#修改主机名
sudo hostnamectl set-hostname nacos-local
sudo vim /etc/sudoers
# 可以sudoers添加下面四行中任意一条
youuser            ALL=(ALL)                ALL
%youuser          ALL=(ALL)                ALL
hsp          ALL=(ALL)                NOPASSWD: ALL
%youuser          ALL=(ALL)                NOPASSWD: ALL
第一行:允许用户youuser执行sudo命令(需要输入密码).
第二行:允许用户组youuser里面的用户执行sudo命令(需要输入密码).
第三行:允许用户youuser执行sudo命令,并且在执行的时候不输入密码.
第四行:允许用户组youuser里面的用户执行sudo命令,并且在执行的时候不输入密码.
#设置hostname
sudo hostnamectl set-hostname nacos-local
#域名映射
sudo vim /etc/hosts
#关闭防火墙
systemctl stop firewalld
systemctl disable firewalld
#时间同步
sudo yum -y install npt nptdate
sudo ntpdate cn.pool.ntp.org
#将硬件时间和系统时间同步
sudo hwclock --systohc 
#安装文件上传软件
#安装jdk
source /etc/profile

#克隆
#修改
#生成秘钥
ssh-keygen -t rsa
#私钥留给自己
ssh-copy-id 192.168.0.2
ssh-copy-id nacos_local
ssh-copy-id nginx_mysql_1 
ssh-copy-id nginx_mysql_2


cd /home/hsp/.ssh
cat /home/hsp/.ssh/known_hosts
#不知道干啥，可以不管
ssh-keygen -f  "/home/hsp/.ssh/known_hosts" -R nginx_mysql_1
ssh-keygen -f  "/root/.ssh/known_hosts" -R 192.168.0.1
#被授权
authorized_keys 
#授权的hosts
known_hosts

```

```shell
#修改网络ip(不会的看视频)
sudo vim /etc/sysconfig/network-scripts/ifcfg-ens33
#修改网络ip方式2 例如里面里面的ip是192.168.100.153 替换称为192.168.100.156
sudo sed -i 's#153#156#' /etc/sysconfig/network-scripts/ifcfg-ens33
service network restart
```

```text
TYPE=Ethernet
PROXY_METHOD=none
BROWSER_ONLY=no
BOOTPROTO=static
DEFROUTE=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=ens33
UUID=ebdfc7e7-e5fe-4a68-88d0-40e18a9dc425
DEVICE=ens33
ONBOOT=yes
IPADDR=192.168.0.106
GATEWAY=192.168.0.2
DNS1=192.168.0.2

```
