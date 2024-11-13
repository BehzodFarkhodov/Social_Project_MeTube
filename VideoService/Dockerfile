FROM openjdk:17-jdk-alpine

EXPOSE 8086

COPY build/libs/VideoService-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar","/app.jar"]