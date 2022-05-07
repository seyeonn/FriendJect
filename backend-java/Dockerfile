FROM openjdk:8

ARG JAR_FILE
COPY build/libs/ssafy-web-project-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", \
 "-jar", \
 "/app.jar"]