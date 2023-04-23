编辑 ~/.cargo/config 文件，添加以下内容：

```text
[source.crates-io]
registry = "https://github.com/rust-lang/crates.io-index"

# 指定镜像(下面几个里选一个)
replace-with = 'ustc'

# 清华大学
[source.tuna]
registry = "https://mirrors.tuna.tsinghua.edu.cn/git/crates.io-index.git"

# 中国科学技术大学
[source.ustc]
registry = "git://mirrors.ustc.edu.cn/crates.io-index"

# 上海交通大学
[source.sjtu]
registry = "https://mirrors.sjtug.sjtu.edu.cn/git/crates.io-index"

# rustcc社区
[source.rustcc0]
registry = "https://code.aliyun.com/rustcc/crates.io-index.git"

[source.rustcc1]
registry="git://crates.rustcc.cn/crates.io-index"

[source.rustcc2]
registry="git://crates.rustcc.com/crates.io-index"

[alias]
b = "build"
t = "test"
r = "run"
rr = "run --release"
br = "build --release"
[target.x86_64-pc-windows-msvc.pq]
rustc-link-search = ["E:/sdk/PostgreSQL/15/lib"]
rustc-link-lib = ["libpq"]
[target.x86_64-pa-windows-msvc.mysqlclient]
rustc-link-search = ["C:/Program Files/MySQL/MySQL Server 5.7/lib"]
rustc-link-lib = ["mysqlclient"]
[target.x86_64-pc-windows-msvc.sqlite3]
rustc-link-search = ["e:/libs/sqlites"]
rustc-link-lib = ["sqlite3"]
```      

C:\Windows\System32\drivers\etc\hosts
```text
140.82.114.3 github.com
199.232.69.194 github.global.ssl.fastly.net
185.199.108.153 assets-cdn.github.com
185.199.109.153 assets-cdn.github.com
185.199.110.153 assets-cdn.github.com
185.199.111.153 assets-cdn.github.com
```

```shell
export RUSTUP_DIST_SERVER=https://mirrors.ustc.edu.cn/rust-static
export RUSTUP_UPDATE_ROOT=https://mirrors.ustc.edu.cn/rust-static/rustup
```

target/release/my_web >> 1.txt > 2&

netstat -aon|findstr 808
yum -y update
yum -y install openssh-server
apt-get install -y openssl
apt install pkg-config