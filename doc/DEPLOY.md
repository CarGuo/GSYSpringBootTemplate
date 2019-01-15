部署简略指南

linux(centos)服务器

下载ssh证书，通过ssh 连接服务器

* 安装 ftp vsftpd
（win 可用过winscp登录，macos可通过scp命令或sftp客户端使用scp登录））

* 安装配置桌面 yum groupinstall "X Window System"

* 配置java、tomact（移除openjdk）

* 配置 mysql ，注意 5.7 之后的 full group 和 emoji表情

* 配置 nginx ，代理静态资源和接口，注意https证书和后缀斜杆问题

* web资源直接打包上传，jar包重启后启动即可。