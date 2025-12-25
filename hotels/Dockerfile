FROM eclipse-temurin:22-alpine

RUN apk add --no-cache curl

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

EXPOSE 8082
CMD ["./mvnw", "spring-boot:run"]