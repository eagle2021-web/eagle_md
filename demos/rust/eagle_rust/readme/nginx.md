#### nginx
```shell
cd /usr/local/nginx
service nginx restart

vim /etc/nginx/nginx.conf
```
server {
    listen  80;   # 监听端口号
    server_name 49.232.8.199; # 监听访问的域名

    location / {
            # 映射到另一个远程连接
                proxy_pass   http://localhost:9999;

    }
}
