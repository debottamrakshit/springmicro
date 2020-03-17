FROM openjdk:8-jdk-alpine
ADD target/bank-service-2-0.0.1-SNAPSHOT.jar bank-service-2.jar
ENTRYPOINT ["java", "-jar", "/bank-service-2.jar"]
EXPOSE 8083