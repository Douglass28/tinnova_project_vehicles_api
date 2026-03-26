FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -Dmaven.test.skip=true && cp target/*.jar app.jar

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /workspace/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
