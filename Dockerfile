# Docker multi-stage build

FROM maven:3.6.3-openjdk-8

ADD . /project
WORKDIR /project

RUN mvn clean install -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

FROM openjdk:8-jdk

COPY --from=0 /project/target/*.jar /app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
