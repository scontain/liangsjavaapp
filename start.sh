#!/bin/bash
set -xe

docker-compose down || true 

./build.sh

./sconify.sh

docker-compose up -d postgres
sleep 3
docker-compose exec -u postgres postgres psql -c 'create database chucknorris;'

docker-compose up java

# docker-compose up java2