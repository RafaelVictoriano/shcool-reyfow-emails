FROM openjdk:17-jdk
WORKDIR /app
COPY target/reyfow-emails-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT java -jar app.jar