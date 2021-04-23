# Spring Microservices Demo

Basic app project from a course.

![gleek-3y-10xWs3G3DsTrwuCrsow](https://user-images.githubusercontent.com/6123841/115467844-d3ca8580-a23a-11eb-8b92-db7707142fa4.png)

## Info

`docker compose up -d`

```
Eureka - http://localhost:8761/
Accounts - http://localhost:8989/accounts/
Bills - http://localhost:8989/bills/
Deposits - http://localhost:8989/deposits/
Postgres - http://localhost:5432/ # postgres:root
RabbitMq - http://localhost:15672/ # guest:guest
```

Demo endpoint requests [demo.http](demo.http).

To make notification-service send emails edit `notification-service/src/main/resources/mail.properties` with proper credentials.
