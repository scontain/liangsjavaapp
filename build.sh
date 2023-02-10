#!/bin/bash

./gradlew clean build -xtest

docker build -t native-java-app .