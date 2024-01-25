# FROM maven:3.8.2-jdk-8 # for Java 8
FROM maven:3.8.5-openjdk-17


# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/tests-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]

