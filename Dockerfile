FROM eclipse-temurin:17-jammy
WORKDIR /app
COPY . .

RUN apt-get update && apt-get install -y maven
RUN mvn clean install -Dmaven.test.skip

EXPOSE 8080
CMD ["java", "-jar", "target/sprint-boot-blog-application-0.0.1-SNAPSHOT.jar"]