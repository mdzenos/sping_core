# docker build -t mdzenos/spring-core:1.0 .
# docker push mdzenos/spring-core:1.0

# ======= Build =======
FROM maven:4.0.0-rc-5-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom và source code
COPY pom.xml .
COPY src ./src

# Build fat jar, skip tests
RUN mvn clean package -DskipTests

# ======= Runner =======
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy jar từ build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port Spring Boot
EXPOSE 8000

# Sử dụng biến môi trường từ .env
# Spring Boot sẽ đọc ${DB_URL}, ${DB_USER}, ${DB_PASS} trong application.properties
ENTRYPOINT ["java", "-jar", "app.jar"]
