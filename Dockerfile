# openjdk:8u111-jdk-alpine has alpine3.4 and sconify_image does not support that
FROM openjdk:8u212-jre-alpine3.9
VOLUME /tmp
ADD /build/libs/*.jar app.jar
ENV JAVA_TOOL_OPTIONS="-Xmx256m"
ENV LD_LIBRARY_PATH=/usr/lib/jvm/java-1.8-openjdk/jre/lib/amd64/server:/usr/lib/jvm/java-1.8-openjdk/jre/lib/amd64:/usr/lib/jvm/java-1.8-openjdk/jre/../lib/amd64
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]