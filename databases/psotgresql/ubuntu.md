```text
= note: /usr/bin/ld: cannot find -lpq
collect2: error: ld returned 1 exit status
```
这个错误表明链接器 ld 无法找到名为 -lpq 的库文件。在 Rust 中，-lpq 表示需要连接到 PostgreSQL 库（libpq），因此出现错误可能是由于 libpq 库未安装或被放置在找不到的路径上。
要解决此问题，请尝试执行以下步骤：
1.确保已经安装了 libpq-dev 包
在大多数 Linux 发行版中，可以使用包管理器来安装 libpq-dev 软件包。例如，在 Ubuntu 和 Debian 上，您可以使用以下命令：
```shell
sudo apt-get install libpq-dev
```

2.指定正确的库文件存放路径
如果已经安装了 libpq-dev 等 PostgreSQL 相关软件包，但仍然收到链接器错误，请确保指定了正确的库文件路径。
在大多数情况下，libpq 库应位于 /usr/lib/ 或 /usr/local/lib/ 目录下。如果是通过源代码编译安装的 PostgreSQL，
可以指定 PG_CONFIG 环境变量来更新配置，如下所示：
```shell
export PG_CONFIG=/path/to/pg_config
cargo build
```
/path/to/pg_config 是用于构建所需链接库信息的 PostgreSQL 配置文件的完整路径。

3.手动安装 libpq 库文件并更新链接器目录
如果以上步骤都无法解决问题，您可以手动安装 libpq 库文件并将其添加到链接器目录中。可以从 PostgreSQL 官方网站下载适用于您的操作系统和架构的安装包，然后将库文件复制到 /usr/lib/ 或 /usr/local/lib/ 目录中，并更新 ldconfig：
```shell
sudo cp /path/to/libpq.so /usr/local/lib/
sudo ldconfig
```
/path/to/libpq.so 是 libpq 库文件的路径。
希望这些步骤可以帮助您解决问题。

### apt安装的postgres设置密码和监听端口
修改 PostgreSQL 配置文件
使用以下命令打开 PostgreSQL 配置文件：

sudo nano /etc/postgresql/12/main/pg_hba.conf
在该文件中找到以下行或添加以下行：

# "local" is for Unix domain socket connections only
local   all             postgres                                peer
将 peer 改为 md5，以要求 Postgres 进行密码验证：

# "local" is for Unix domain socket connections only
local   all             postgres                                md5
重启 PostgreSQL 服务
使用以下命令重启 PostgreSQL 服务以应用更改：

sudo systemctl restart postgresql.service
切换到 PostgreSQL 用户并重置密码
切换到 PostgreSQL 账户，并使用以下命令重新设置 PostgreSQL 密码：

sudo -u postgres psql
ALTER USER postgres PASSWORD 'new_password';
将 new_password 替换为您想要的新密码。

恢复 PostgreSQL 配置文件设置
在重设密码后，记得再次编辑 /etc/postgresql/12/main/pg_hba.conf 文件，并将修改前的 peer 恢复为原始状态。

重启 PostgreSQL 服务
最后，重启 PostgreSQL 服务以使所有更改生效：

sudo systemctl restart postgresql.service
请注意，此方法假定您具有适当的特权和对 Postgres 数据库服务器完整访问权限.

#### 监听端口
sudo nano /etc/postgresql/12/main/pg_hba.conf
listen_addresses = '*'
port = 5433
