#!/usr/bin/env bash

docker kill nginx &>/dev/null
docker rm nginx &>/dev/null

docker run --rm -p 80:80 -d -v $(pwd)/www:/var/www/html --name nginx debian-nginx &>/dev/null
docker exec -it nginx bash
