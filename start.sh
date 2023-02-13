#!/bin/bash
set -xe

COMPOSE_COMMAND="docker-compose"
# if docker is not installed, abort execution
detect_docker() {
    which docker > /dev/null
    if [ $? -ne 0 ]; then
        echo -e "docker is not installed. please install it\nexiting now"
        exit 1
    fi

    # look for 'docker compose' plugin
    docker compose --help > /dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo "changed!!!"
        COMPOSE_COMMAND="docker compose"
    fi
}

detect_docker

$COMPOSE_COMMAND down || true

./build.sh

./sconify.sh

$COMPOSE_COMMAND up -d postgres
sleep 3
$COMPOSE_COMMAND exec -u postgres postgres psql -c 'create database chucknorris;'

$COMPOSE_COMMAND up java

# $COMPOSE_COMMAND up java2
