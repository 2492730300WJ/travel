#基础镜像
FROM openjdk:8
#作者
MAINTAINER wj <2492730300@qq.com>

VOLUME /tmp
#指定配置文件，以及jar包在服务器上的路径
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/root/Jenkins-in/xm-0.0.1-SNAPSHOT.jar"]
#暴露端口
EXPOSE 9999