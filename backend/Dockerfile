FROM openjdk:17-jdk-alpine
MAINTAINER Burduzhan Ruslan
#VOLUME /main-app
ADD target/backend-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java","-jar","backend.jar"]
