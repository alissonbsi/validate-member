# Stage 1: Build com Maven + JDK 21
FROM maven:3.9.9-eclipse-temurin-21-jammy AS build

WORKDIR /app

# Copia pom.xml primeiro (cache)
COPY pom.xml .

RUN mvn dependency:go-offline -B

# Copia código
COPY src ./src

# Build do jar
RUN mvn clean package -DskipTests

# Stage 2: Runtime com JDK 21
FROM eclipse-temurin:21-jdk-jammy AS runtime

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/target/validade-member-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]