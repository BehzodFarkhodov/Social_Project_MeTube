FROM openjdk:17-jdk-alpine

EXPOSE 8085

COPY build/libs/ChannelService-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar","/app.jar"]