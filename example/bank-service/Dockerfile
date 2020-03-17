FROM openjdk:8-jdk-alpine
ADD target/bank-service-0.0.1-SNAPSHOT.jar bank-service.jar
ENTRYPOINT ["java", "-jar", "/bank-service.jar"]
EXPOSE 8081