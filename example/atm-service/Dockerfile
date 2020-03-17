FROM openjdk:8-jdk-alpine
ADD target/atm-service-0.0.1-SNAPSHOT.jar atm-service.jar
ENTRYPOINT ["java", "-jar", "/atm-service.jar"]
EXPOSE 8082