FROM openjdk:17-jdk-alpine
MAINTAINER Burduzhan Ruslan
VOLUME /main-app
ADD target/backend-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","backend.jar"]