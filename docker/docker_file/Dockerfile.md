```Dockerfile
FROM python:3.8-slim-buster
WORKDIR /app 
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
CMD ["python", "app.py"]
```
```text
这段 Dockerfile 文件用于构建 Docker 镜像，其中包含了一些指令。COPY . .是其中的一个指令，其作用是将当前目录中的所有文件复制到镜像的 /app 目录内。
这个指令的第一个 . 表示当前工作目录，也就是包含 Dockerfile 文件的目录；而第二个 . 表示当前工作目录下的所有文件，也就是将当前工作目录下的所有文件都复制到容器中的 /app 目录下。由于在该 Dockerfile 文件中使用了 WORKDIR /app 指令，所以后面的操作会默认在 /app 目录下进行，因此COPY . .会将当前工作目录下的文件全部复制到容器的 /app 目录下，从而完成应用程序的构建部署工作。
总体来说，这段 Dockerfile 主要完成了以下步骤：
以 Python 3.8-slim-buster 为基础镜像；
设置工作目录为 /app 目录；
将本地的 requirements.txt 文件复制到镜像的 /app 目录下；
在镜像中执行 pip install 安装相关依赖库；
将当前工作目录下的所有文件复制到镜像的 /app 目录下；
最后运行 app.py 应用程序。
```
## 2.编写app.py
在工作目录中创建一个名为 "app.py" 的 Python 脚本，并将以下代码添加到其中创建一个简单的 Flask 应用：
```python
from flask import Flask
app = Flask(__name__)

@app.route('/', methods=['GET'])
def hello():
    return 'Hello, World!'

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=8080)
```
这个 Flask 应用只有一个路由，当访问根路由时将返回 'Hello, World!' 的字符串。运行程序需要在requirements.txt中添加 Flask 依赖项以确保程序能够正常启动。
## 3.构建镜像
在命令行终端中运行以下命令在当前目录中构建 Docker 镜像：
```shell
docker build -t my-python-app .
```
在这个命令中，"-t" 选项用于指定镜像的名称（在这种情况下为 "my-python-app"），"." 指示 Docker 使用当前目录下的 Dockerfile 来构建镜像。注意这里有一个 . 点表示当前文件夹，并且不要忘记这一点。
## 4.运行容器
使用以下命令在本地端口8080上启动容器：
```shell
docker run -p 8080:8080 my-python-app
```
在这个命令中，"-p" 选项用于将容器内的8080端口映射到主机的8080端口。 "-p" 后的第一个数字表示主机的端口号，而第二个数字表示容器内部的端口号。
## 5.测试服务
现在可以通过浏览器或者curl在本地访问http://localhost:8080，或者使用其他HTTP客户端程序，例如Postman等。
如果一切正常，您应该会看到输出 "Hello, World!" 的字符串。
希望这些步骤对您有所帮助，祝开发愉快！