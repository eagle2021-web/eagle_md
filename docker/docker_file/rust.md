```dockerfile
FROM eagle_python:1
COPY rustup-init.sh /rustup-init.sh

# 设置环境变量以自动选择安装选项
ENV RUSTUP_INIT_SKIP_PATH_CHECK=true
ENV RUSTUP_TOOLCHAIN=stable
#ENV RUSTUP_UPDATE_ROOT=https://mirrors.ustc.edu.cn/rust-static/rustup
#ENV RUSTUP_DIST_SERVER=https://mirrors.ustc.edu.cn/rust-static

# 执行 rustup-init.sh 脚本并设置默认选项
RUN chmod +x /rustup-init.sh
RUN /rustup-init.sh -y

# 设置 PATH 环境变量以包含 Rust 工具链
#ENV PATH="/root/.cargo/bin:${PATH}"
# 运行 source 命令
RUN /bin/bash -c "source ${HOME}/.cargo/env"
# ENV CARGO_CONFIG="[source.crates-io]\nregistry = \"https://github.com/rust-lang/crates.io-index\"\n"
# RUN mkdir -p /root/.cargo
# 复制 cargo 配置文件
# RUN echo "$CARGO_CONFIG" > /root/.cargo/config
ENV PATH="/root/.cargo/bin:${PATH}"
RUN echo "export PATH=/root/.cargo/bin:${PATH}" >> /etc/profile.d/my_env.sh
WORKDIR /
RUN cargo new hello
WORKDIR /hello
RUN cargo add memchr
WORKDIR /
```

```dockerfile
FROM eagle_python:1
COPY rustup-init.sh /rustup-init.sh

# 设置环境变量以自动选择安装选项
ENV RUSTUP_INIT_SKIP_PATH_CHECK=true
ENV RUSTUP_TOOLCHAIN=stable
ENV RUSTUP_UPDATE_ROOT=https://mirrors.ustc.edu.cn/rust-static/rustup
ENV RUSTUP_DIST_SERVER=https://mirrors.ustc.edu.cn/rust-static

# 执行 rustup-init.sh 脚本并设置默认选项
RUN chmod +x /rustup-init.sh
RUN /rustup-init.sh -y

# 设置 PATH 环境变量以包含 Rust 工具链
#ENV PATH="/root/.cargo/bin:${PATH}"
# 运行 source 命令
RUN /bin/bash -c "source $HOME/.cargo/env"
# ENV CARGO_CONFIG="[source.crates-io]\nregistry = \"https://github.com/rust-lang/crates.io-index\"\n"
# RUN mkdir -p /root/.cargo
# 复制 cargo 配置文件
# RUN echo "$CARGO_CONFIG" > /root/.cargo/config
ENV PATH="/root/.cargo/bin:${PATH}"
RUN echo "export PATH=/root/.cargo/bin:${PATH}" >> /etc/profile.d/my_env.sh
WORKDIR /
RUN cargo new hello
WORKDIR /hello
RUN cargo add memchr
WORKDIR /
```
docker build --no-cache -t rust_m1:1 .