FROM openjdk:8-jdk-alpine
ARG JAR_FILE=smartprofile-web/target/*.jar
COPY ${JAR_FILE} /usr/app/
WORKDIR /usr/app/
RUN sh -c 'touch smartprofile-api.jar'
ENTRYPOINT ["java","-jar","smartprofile-api.jar","--spring.profiles.active=aws"]

