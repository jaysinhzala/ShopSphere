# Step 1 - Use Java 21 as base image
FROM openjdk:21-jdk-slim

# Step 2 - Set working directory inside container
WORKDIR /app

# Step 3 - Copy the built jar file into container
COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar

# Step 4 - Expose port 8083
EXPOSE 8083

# Step 5 - Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]