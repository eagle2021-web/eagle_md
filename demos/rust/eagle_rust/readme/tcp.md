```shell
ip tuntap add mode tap name tap-rust user eagle
# 如果已经添加了tag-rust 再次添加会报错如左
ioctl(TUNSETIFF): Device or resource busy 
# 删除这个设备
[root@mysqlMaster ~]# ip tuntap del mode tap name tap-rust

# 建立一个叫做tap-rust的网络设备，并且启用它
ip link set tap-rust up
# 为此设备分配IP地址192.168.42.100
ip addr add 192.168.42.100/24 dev tap-rust
# 通过附加一条新规则（-A POSTROUTING）,动态地把IP地址映射到一个设备上（-j MASQUERADE）,让IP数据包能够到达这个源IP地址掩码（-s 192.168.42.0/42）
iptables -t nat -A POSTROUTING -s 192.168.42.0/24 -j MASQUERADE
# 启用内核的IPV4包转发功能
sysctl net.ipv4.ip_forward=1
```