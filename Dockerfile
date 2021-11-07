FROM openjdk:11

WORKDIR /opt/app-api

COPY /target/rogastore-api*.jar rogastore-api.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080

CMD java -jar app_store_api.jar