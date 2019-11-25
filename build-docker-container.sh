#!/usr/bin/env bash

[ ! -e target/login-demo-1.0.0-SNAPSHOT.jar ] && mvn clean package -DskipTests
docker build . -t debian-login-demo
