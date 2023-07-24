FROM maven:3.8.6-openjdk-11-slim AS builder

WORKDIR /app
COPY . /app/
RUN mvn -s /app/for_docker/maven_settings.xml -f /app/pom.xml clean package
RUN mvn -s /app/for_docker/maven_settings.xml -f /app/pom.xml verify

FROM bellsoft/liberica-openjre-alpine:11
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]