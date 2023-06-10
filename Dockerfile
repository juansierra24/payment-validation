# Use a base image with Java 11 installed
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle build files to the container
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle gradle

# Copy the source code to the container
COPY src src

# Build the application using Gradle
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon

# Set the JAR file name and location
ARG JAR_FILE=build/libs/*.jar

# Copy the JAR file to the container
COPY ${JAR_FILE} app.jar

# Expose the application port
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
