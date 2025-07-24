#!/bin/bash
podman run -d \
  --name mysql-container \
  -e MYSQL_ROOT_PASSWORD=123456 \
  -p 3306:3306 \
  mysql:latest