#!/usr/bin/env bash

docker kill login-demo &>/dev/null
docker rm login-demo &>/dev/null
docker run --rm -p 8080:8080 -d --name login-demo debian-login-demo
docker exec -it login-demo bash
