
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/bookingSchedule-0.0.1-SNAPSHOT.jar session-request-service.jar

EXPOSE 9700

ENTRYPOINT ["java", "-jar", "session-request-service.jar"]

ENV TZ=Asia/Seoul