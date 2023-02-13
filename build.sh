#!/bin/bash
set -e

./gradlew clean build -xtest

docker build -t native-java-app .
