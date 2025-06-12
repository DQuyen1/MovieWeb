# Use Java 17 base image
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Copy everything from your project into the container
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080
ENV PORT=8080

# Build the project (skip tests to save time)


# Run the Spring Boot app
CMD ["java", "-jar", "app.jar"]
