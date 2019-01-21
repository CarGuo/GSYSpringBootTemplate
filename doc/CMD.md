


##### java -jar xxx.jar --spring.profiles.active=dev & 表示使用测试环境的配置
##### java -jar xxx.jar --spring.profiles.active=pro & 表示使用生产环境的配置
##### nohup java -jar template-0.0.1-SNAPSHOT.jar --spring.profiles.active=pro  > log.file  2>&1 &
##### nohup java -jar template-0.0.1-SNAPSHOT.jar > log.file  2>&1 &
##### 查看运行的jar的pid,看看jar程序是否运行:ps -ef|grep java
##### 如果文件过大，可在不停止jar包的情况下清空日志文件:用命令 cp /dev/null nohup.out  清空文件
##### kill -2 pid


##### win 后台运行
##### @echo off
##### start javaw -jar xx.jar
##### exit

##### win 查看java进程
##### jps -l（显示java进程的Id和软件路径及名称）


##### win关闭进程
##### taskkill /F /pid 26768

### docker

##### 进入docker
##### docker exec -it mysql /bin/bash 

##### 清除所有未使用容器
##### docker rm $(docker ps -a -q)  

##### 进mysql
##### mysql -uroot -p

##### 查看ip
##### docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)

##### 运行
##### docker-compose up

##### 状态
##### docker-compose ps