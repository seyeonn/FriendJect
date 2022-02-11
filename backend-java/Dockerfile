FROM openjdk:8 AS builder
WORKDIR /backend
COPY . .
RUN chmod +x ./gradlew

RUN ./gradlew :module-web:clean build

FROM adoptopenjdk:8-jdk
COPY --from=builder /backend/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar"]