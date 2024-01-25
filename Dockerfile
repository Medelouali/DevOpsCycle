# Use an official OpenJDK runtime as a parent image
FROM khipu/openjdk17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/tests-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]
