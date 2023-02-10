#!/usr/bin/env bash

set -euo pipefail
set -x

SCONIFY_IMAGE="registry.scontain.com/sconecuratedimages/community-edition-sconify-image:5.8.0-rc1"
NATIVE_IMAGE="native-java-app:latest"
ENCRYPTED_IMAGE="sconify-java-app-sconify:latest"

BINARY="java"

SERVICE="java-postgres-service-""$RANDOM"
SESSION="java-postgres-session-""$RANDOM"
NAMESPACE="java-postgres-ns-""$RANDOM""$RANDOM""$RANDOM"
SCONE_HEAP="4G"
SCONE_STACK="8M"
SCONE_ALLOW_DLOPEN="1"
SCONE_FORK="0"
CAS_ADDR="5-7-0.scone-cas.cf"
LAS_ADDR="las"

COMMAND="java -Dapplication.gossip.url=http://localhost:8080/api/jokes/random -jar /app.jar"

docker run --rm \
     -v /var/run/docker.sock:/var/run/docker.sock \
     -v $HOME/.docker/config.json:/root/.docker/config.json \
     $SCONIFY_IMAGE sconify_image \
     --from="$NATIVE_IMAGE" --to="$ENCRYPTED_IMAGE" \
     --fs-dir=/ \
     --binary-fs \
     --host-path=/tmp \
     --host-path=/etc/hosts \
     --host-path=/etc/resolv.conf \
     --cas="$CAS_ADDR" --las="$LAS_ADDR" \
     --cas-debug \
     --create-namespace \
     --service-name="$SERVICE" --name="$SESSION" --namespace="$NAMESPACE" \
     --allow-tcb-vulnerabilities \
     --allow-debug-mode \
     --binary="$BINARY" \
     --heap="$SCONE_HEAP" --stack="$SCONE_STACK" --dlopen="$SCONE_ALLOW_DLOPEN" \
     --fork=$SCONE_FORK --mprotect=1 \
     --command="$COMMAND" \
     --allow-tcb-vulnerabilities \
     --allow-debug-mode \
     --verbose


SERVICE="java-postgres-service-""$RANDOM"
SESSION="java-postgres-session-""$RANDOM"
NAMESPACE="java-postgres-ns-""$RANDOM""$RANDOM""$RANDOM"
COMMAND="java -Dapplication.gossip.url=http://localhost:8081/api/jokes/random  -Dserver.port=8081 -jar /app.jar"
ENCRYPTED_IMAGE="sconify-java-app-sconify:latest-2"

docker run --rm \
     -v /var/run/docker.sock:/var/run/docker.sock \
     -v $HOME/.docker/config.json:/root/.docker/config.json \
     $SCONIFY_IMAGE sconify_image \
     --from="$NATIVE_IMAGE" --to="$ENCRYPTED_IMAGE" \
     --fs-dir=/ \
     --binary-fs \
     --host-path=/tmp \
     --host-path=/etc/hosts \
     --host-path=/etc/resolv.conf \
     --cas="$CAS_ADDR" --las="$LAS_ADDR" \
     --cas-debug \
     --create-namespace \
     --service-name="$SERVICE" --name="$SESSION" --namespace="$NAMESPACE" \
     --allow-tcb-vulnerabilities \
     --allow-debug-mode \
     --binary="$BINARY" \
     --heap="$SCONE_HEAP" --stack="$SCONE_STACK" --dlopen="$SCONE_ALLOW_DLOPEN" \
     --fork=$SCONE_FORK --mprotect=1 \
     --command="$COMMAND" \
     --allow-tcb-vulnerabilities \
     --allow-debug-mode \
     --verbose
