FROM openjdk:8-jdk-alpine
ADD target/eurekaserver-2-0.0.1-SNAPSHOT.jar eurekaserver-2.jar
ENTRYPOINT ["java", "-jar", "/eurekaserver-2.jar"]
EXPOSE 8762