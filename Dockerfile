# Use an official OpenJDK runtime as the base image
FROM openjdk:23

# Set the working directory in the container
WORKDIR /app

# Copy the Maven/Gradle build artifact into the container
COPY target/CRUD-project-0.0.1-SNAPSHOT.jar app.jar

# Create a volume to persist H2 database files
VOLUME /app/dataBase

# Expose the port your application runs on
EXPOSE 6060

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
