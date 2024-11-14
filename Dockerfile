# Use the official Maven image to build the application
# This builds the application using a multi-stage build to keep the final image small
FROM maven:3.8.5-openjdk-11 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the container
COPY pom.xml .
COPY src ./src

# Package the application (this will create a JAR file in the target directory)
RUN mvn clean package -DskipTests

# Use a minimal JDK image to run the application
FROM openjdk:11-jre-slim

# Set the working directory in the runtime image
WORKDIR /app

# Copy the JAR file from the build stage to the runtime image
COPY --from=build /app/target/kaddem-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8089

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]