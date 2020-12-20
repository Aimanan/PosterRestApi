FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD build/libs/poster-0.0.1-SNAPSHOT.jar poster.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/poster.jar"]