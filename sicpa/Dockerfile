# Maven 
FROM maven:3.8.1-openjdk-11-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean -e -B package
 
#Java
FROM openjdk:11-jre-slim-buster
EXPOSE 8080
WORKDIR /app
COPY --from=builder /app/target/sicpa-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "./sicpa-0.0.1-SNAPSHOT.jar"]
