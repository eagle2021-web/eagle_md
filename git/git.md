## hosts文件权限
hosts右键->安全->编辑->写入
## hosts文件配置
```text
140.82.112.3 github.com
151.101.1.194 github.global.ssl.fastly.net
```
## git仓库初始化
```shell
echo "# eagle_python" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/eagle2021-web/eagle_python.git
git push -u origin main
```