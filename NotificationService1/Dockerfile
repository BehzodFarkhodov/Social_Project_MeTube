FROM openjdk:17-jdk-alpine

EXPOSE 8087

COPY build/libs/NotificationService1-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]