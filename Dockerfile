FROM gradle:jdk11 AS builder

WORKDIR /home/gradle
COPY . .
RUN ./gradlew assemble copyJarToServerJar

FROM registry.access.redhat.com/ubi8/openjdk-11:1.3-3

COPY --from=builder /home/gradle/build/libs/server.jar ./server.jar

EXPOSE 9080/tcp

CMD ["java", "-jar", "./server.jar"]
