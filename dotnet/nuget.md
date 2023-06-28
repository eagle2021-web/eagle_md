https://www.nuget.org/downloads 下载
https://www.nuget.org/ 仓库
https://www.mono-project.com/download/stable/#download-lin 安装mono
/etc/profile 配置 
```shell
alias nuget="$HOME/nuget/nuget.exe"
```

hello.csproj
```csproj
<Project Sdk="Microsoft.NET.Sdk">

  <PropertyGroup>
    <OutputType>Exe</OutputType>
    <TargetFramework>netstandard1.0</TargetFramework>
  </PropertyGroup>

</Project>
```

```shell
dotnet add package Newtonsoft.Json --version 13.0.3
```
