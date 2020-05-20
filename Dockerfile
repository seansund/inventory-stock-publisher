FROM registry.access.redhat.com/ubi8/openjdk-11:1.3-3 AS builder

WORKDIR /home/jboss
RUN echo $PWD
COPY . .
RUN ./gradlew assemble copyJarToServerJar --no-daemon

FROM registry.access.redhat.com/ubi8/openjdk-11:1.3-3

COPY --from=builder /home/jboss/build/libs/server.jar ./server.jar

EXPOSE 9080/tcp

CMD ["java", "-jar", "./server.jar"]
