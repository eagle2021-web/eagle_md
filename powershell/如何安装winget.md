

```text
以下是在 Windows 10 上安装 Winget 的步骤：
打开 Microsoft Store 应用商店。
搜索“msixbundle”。
点击“安装”按钮。
打开 PowerShell 应用程序。
运行以下命令，以安装包管理器：
github下载
https://github.com/microsoft/winget-cli/releases
https://github.com/microsoft/winget-cli/releases/download/v1.4.10173/Microsoft.DesktopAppInstaller_8wekyb3d8bbwe.msixbundle
Add-AppxPackage -Path ???\Microsoft.DesktopAppInstaller_8wekyb3d8bbwe.msixbundle
安装完成后，可以通过在 PowerShell 中输入 "winget" 命令来验证安装是否成功。如果成功，则应显示与winget相关的帮助信息。

注意：Winget 可能需要一些系统配置才能正常运行，例如必须打开 Powershell 的执行策略、设置代理等。因此，在安装 Winget 之前，请先了解如何使其在您的系统上正常运行。

```
