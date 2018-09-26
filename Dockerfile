FROM openjdk:8-jdk-alpine

LABEL description="docker container to run sinapps"

VOLUME /tmp

#ARG JAR_FILE=target/*.jar

RUN mkdir -p /app/logs

WORKDIR /app

#COPY ${JAR_FILE} microservices.jar
