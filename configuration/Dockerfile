FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8001
COPY build/libs/configuration-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "configuration-0.0.1-SNAPSHOT.jar"]