FROM openjdk:8
EXPOSE 8085
ADD smartprofile-web/target/smartprofile-api.jar smartprofile-api.jar
ENTRYPOINT ["java","-jar","/smartprofile-api.jar"]

