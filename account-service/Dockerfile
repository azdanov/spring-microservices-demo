FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8081
COPY build/libs/account-service-0.0.1-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "gateway:8989", "--timeout=70", "--", "java", "-jar", "account-service-0.0.1-SNAPSHOT.jar"]