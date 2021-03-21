FROM java:8-jdk-alpine
COPY ./target/contacts-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=in-container", "contacts-0.0.1-SNAPSHOT.jar"]