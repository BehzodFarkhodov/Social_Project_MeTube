FROM openjdk:17-jdk-alpine

EXPOSE 8084

COPY build/libs/AuthService-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]