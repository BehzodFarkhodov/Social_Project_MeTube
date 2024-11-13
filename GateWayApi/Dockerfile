FROM openjdk:17-jdk-alpine

LABEL authors = "Sardorbek_Boboyev1"

COPY build/libs/ApiGateway-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]