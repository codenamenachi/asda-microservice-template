FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER asda
ARG JAR_FILE=target/asda-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]