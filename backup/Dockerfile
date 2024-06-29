FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]