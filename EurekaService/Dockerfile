FROM openjdk:17-jdk-alpine

EXPOSE 8761


COPY build/libs/EurekaService-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar","app.jar"]