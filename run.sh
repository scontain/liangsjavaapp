#!/bin/bash

./check_prerequisites.sh

# clean
sudo rm -rf target

# determine postgres IP/URL and update spring.datasource.url in application.properties

# build native java program
./gradlew clean build -xtest

#sconify
SCONECTL_REPO=registry.scontain.com/cicd sconectl apply -f service.yaml -h templates-genservice -vvvvvvvv --mode=debug
SCONECTL_REPO=registry.scontain.com/cicd sconectl apply -f mesh.yaml --release v1 -vvvvvvv --mode=debug

#deploy
helm install v1 target/helm/