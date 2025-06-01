FROM openjdk:17-jdk-alpine
EXPOSE 8083
ADD target/stock-service-0.0.1-SNAPSHOT.jar stock-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/stock-service-0.0.1-SNAPSHOT.jar" ]