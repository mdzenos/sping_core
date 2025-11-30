# docker build -t mdzenos/spring-core:1.0 .
# docker push mdzenos/spring-core:1.0

# Build Stage: Build fat jar
FROM maven:4.0.0-rc-5-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml và source
COPY pom.xml .
COPY src ./src

# Build fat jar, skip tests
RUN mvn clean package -DskipTests

# Runner Stage: Jar + Maven
FROM eclipse-temurin:17-jdk-noble
WORKDIR /app

# Cài Maven để chạy dev container
RUN apt-get update && \
    apt-get install -y maven bash nano inotify-tools && \
    rm -rf /var/lib/apt/lists/*

# Copy jar từ build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port Spring Boot
EXPOSE 8000

# Biến môi trường đọc từ .env
ENV SPRING_PROFILES_ACTIVE=prod

# Mặc định chạy jar production
ENTRYPOINT ["java", "-jar", "app.jar"]
