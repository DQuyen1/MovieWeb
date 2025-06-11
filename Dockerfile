# Use Java 17 base image
FROM eclipse-temurin:17-jdk


ENV HOST 0.0.0.0


# Set working directory inside the container
WORKDIR /app

# Copy everything from your project into the container
COPY . .

# Make mvnw executable (for Linux builds)
RUN chmod +x mvnw

# Expose port
EXPOSE 8080


# Build the project (skip tests to save time)
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot app
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
