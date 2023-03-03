```text
虚拟机使用的是此版本 VMware Workstation 不支持的硬件版本。  模块“Upgrade”启动失败。  未能启动虚拟机。
```
#### 解决方案
```text
base.vmx
19 -> 16版本
virtualHW.version = "16"
```
https://blog.csdn.net/qq237696047/article/details/106266999
```yaml

```