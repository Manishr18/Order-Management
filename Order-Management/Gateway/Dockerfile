FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD target/Gateway-0.0.1-SNAPSHOT.jar Gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/Gateway-0.0.1-SNAPSHOT.jar" ]