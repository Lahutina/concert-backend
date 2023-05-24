FROM openjdk:17.0.2-jdk-slim-buster
COPY /target/concert-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]