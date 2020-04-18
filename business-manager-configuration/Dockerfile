FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
VOLUME /tmp
COPY target/business-manager-configuration-1.0-SNAPSHOT.jar app.jar
HEALTHCHECK --interval=5s --timeout=3s CMD curl --fail http://BMCONFIGURATION:8888/actuator/health || exit 1
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

