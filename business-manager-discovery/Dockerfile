FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
VOLUME /tmp
COPY target/business-manager-discovery-1.0-SNAPSHOT.jar app.jar
HEALTHCHECK --interval=5s --timeout=3s CMD curl --fail http://BMDISCOVERY:8761/actuator/health || exit 1
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
