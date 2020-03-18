FROM openjdk:8-jdk-alpine
ADD target/eurekaserver-0.0.1-SNAPSHOT.jar app.jar
COPY start.sh start.sh
RUN ["chmod", "+x", "start.sh"]
ENTRYPOINT ["/bin/sh","./start.sh"]
EXPOSE 8761