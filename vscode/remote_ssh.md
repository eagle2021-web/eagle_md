0. https://github.com/PowerShell/Win32-OpenSSH/releases/download/v8.1.0.0p1-Beta/OpenSSH-Win64.zip
1. 下载解压到C:\Program Files\OpenSSH
2. 设置系统环境变量path的值追加C:\Program Files\OpenSSH
3. 检查是否安装cmd运行ssh 或者打开 C:\Windows\System32\OpenSSH 查看是否有ssh.exe

4. https://code.visualstudio.com/Download#
user版会安装在当前计算机帐户目录,这意味着如果使用另一个帐号登陆计算机将无法使用别人安装的
system版本可以安装在非用户目录,例如C盘根目录,任何帐户都可以使用.
5. 打开vs界面按下F1输入Remote-SSH:settings
6. 找到Remote-SSH:Path 设置为C:\Program Files\OpenSSH\ssh.exe
7. 免密登录
   1. windows 生成密钥文件 cmd 打开 输入ssh-keygen一路回车将在C:\Users\86176.ssh\下生成2个文件
   2. 复制或者拷贝C:\Users\86176.ssh\id_rsa\id_rsa.pub 中的 内容 到远端服务器的/root/.ssh/authorized_keys中
8. 修改vs_code的连接信息
```text
Host root
HostName 192.168.0.11
User root 
IdentityFile C:\Users\86176\.ssh\id_rsa
```