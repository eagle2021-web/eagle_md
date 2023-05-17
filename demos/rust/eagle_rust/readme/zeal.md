解决zeal下载缓慢的问题
1.安装zeal

2.下载速度的问题

Zeal的文档都保存在国外的服务器上，如果用Zeal直接下载，速度极慢，而且容易中断。下面重点介绍如何自己拼接zeal的文档下载地址。

2.1 查找要下载的离线文档的名称name值，像我要下载JavaScript的离线文档，就直接搜JavaScript，然后name值要跟搜出来的内容相同

在Docsets中找到你想要的文档，记住名字

在http://api.zealdocs.org/v1/docsets中用ctrl+f查找：“title”:“你要的文档名”（要区分大小写），复制其中的name的值。

用这个格式http://{city_name}.kapeli.com/feeds/{name}.tgz，把city_name换为响应服务器城市，name换位步骤2的name（注意去除大括号）。



2.2拼接下载链接

zeal离线文档的下载链接格式是：

http://{city_name}.kapeli.com/feeds/{name}.tgz  那么我要下载JavaScript的链接就为http://city_name.kapeli.com/feeds/JavaScript.tgz

name: 是刚刚找好的要下载文档的name值

city_anme: 是服务器所在的城市

服务器所在的城市如下：

frankfurt, london, newyork, sanfrancisco, singapore, sydney, tokyo

我大致测试了几个服务器的下载速度，把你要下的文档的name值替换掉下面的链接的{name}即可。

快——> 慢排序

http://tokyo.kapeli.com/feeds/{name}.tgz

http://singapore.kapeli.com/feeds/{name}.tgz

http://sanfrancisco.kapeli.com/feeds/{name}.tgz

http://frankfurt.kapeli.com/feeds/{name}.tgz

http://newyork.kapeli.com/feeds/{name}.tgz

http://sydney.kapeli.com/feeds/{name}.tgz

http://london.kapeli.com/feeds/{name}.tgz

#### 举例
```bash
wget https://tokyo.kapeli.com/feeds/MongoDB.tgz
```

3.下载完成后，找到下载的文件，后缀名应该为tgz，通过解压后变成tar，再解压一次就可以得到你想要的离线文件夹。

4.接着找到zeal离线文档储存的路径，点击Edit——>preferences——>Docset storage就是你离线文档储存的位置，然后直接把解压好的文件夹复制到那个位置，最后重启zeal即可。