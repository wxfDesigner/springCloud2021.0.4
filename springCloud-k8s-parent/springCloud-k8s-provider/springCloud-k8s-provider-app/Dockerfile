#FROM openjdk:8-jdk-alpine
FROM openjdk:8-jre
ADD ./target/springCloud-k8s-provider-app.jar /app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-jar", "/app.jar"]