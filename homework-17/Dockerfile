#Building application
FROM maven:3.6 AS build
COPY src src
COPY pom.xml pom.xml
RUN mvn package

FROM openjdk:16
COPY --from=build target/homework-17-0.0.1.jar homework-17.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "homework-17.jar"]