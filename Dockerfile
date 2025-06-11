# Use a base image with Java runtime
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the jar file (adjust the filename)
COPY target/movie-api.jar app.jar


# Expose port# Use a base image with Java runtime
             FROM eclipse-temurin:17-jdk

             # Set working directory
             WORKDIR /app

             # Copy the jar file (adjust the filename)
             COPY target/movie-api.jar app.jar


             # Expose port
             EXPOSE 8080

             # Run the jar
             CMD ["java", "-jar", "app.jar"]

EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "app.jar"]
