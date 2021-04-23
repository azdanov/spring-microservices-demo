FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8761
COPY build/libs/registry-0.0.1-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "configuration:8001", "--timeout=70", "--", "java", "-jar", "registry-0.0.1-SNAPSHOT.jar"]