FROM openjdk:17-alpine

MAINTAINER 2024_6SOAT

COPY target/tech-challenge-1.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080