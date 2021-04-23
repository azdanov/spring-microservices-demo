FROM adoptopenjdk/openjdk11:alpine-slim
COPY build/libs/notification-service-0.0.1-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "bill-service:8082", "--timeout=110", "--", "java", "-jar", "notification-service-0.0.1-SNAPSHOT.jar"]