# payment validation service

## requiriments

This project was build using Java 11 y Gradle 7.5.1, next you will find a list of used tools:

- Intellij IDE
- Java Extension Pack
- Spring Boot Extension Pack
- Gradle Tasks
- Lombok Annotations Support for VS Code
- Gradle 7.5.1
- JVM 11.0.15

## Development server

1. build the application using `./gradlew build`
2. use your preferred IDE for run the app or use `java -jar ./build/libs/payment-0.0.1-SNAPSHOT.jar`
3. the application will start in port 8080

### Using Docker compose

1. navigate to the root directory and run `docker-compose up -d` 
  this will run the application in port 8080 inside a container.

## Endpoints

- **LOCAL**

    -  http://127.0.0.1:8080/api/v1/payment/validate

---
