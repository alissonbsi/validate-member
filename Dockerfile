# Stage 1: Build
FROM maven:3.9.3-eclipse-temurin-21 AS build

WORKDIR /app

# Copia pom.xml para cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código
COPY src ./src

# Build do jar
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk-jammy AS runtime

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/target/validade-member-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]