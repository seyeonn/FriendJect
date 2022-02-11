FROM openjdk:8 AS builder
WORKDIR /backend
COPY . .
RUN chmod +x ./gradlew

RUN ["./gradlew", "clean", "build", "--stacktrace", "--exclude-task", "test"]


FROM openjdk:8-jre-slim
COPY --from=builder /backend/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar"]