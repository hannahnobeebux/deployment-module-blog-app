FROM adoptopenjdk:11-jdk-hotspot
COPY . /app
WORKDIR /app
RUN ./mvnw package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/sprint-boot-blog-application.jar"]