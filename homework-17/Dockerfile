#Building application
FROM maven AS build
COPY src src
COPY pom.xml pom.xml
RUN mvn package

FROM openjdk
COPY --from=build target/homework-17-0.0.1.jar homework-17.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "homework-17.jar"]