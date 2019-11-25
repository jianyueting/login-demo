FROM debian-openjdk8

MAINTAINER Jian Yueting <jianyueting@gmail.com>

COPY target/login-demo-1.0.0-SNAPSHOT.jar /login-demo.jar
COPY startup.sh /startup.sh

RUN chmod +x /startup.sh

EXPOSE 8080
CMD ["/startup.sh"]
