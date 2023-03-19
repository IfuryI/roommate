FROM openjdk:17-alpine

ARG EMAIL
ARG PASSWORD

ADD roommate-application/target/roommate-application.jar roommate-application.jar

EXPOSE 8080


ENTRYPOINT ["java", "-Dspring.mail.username=${EMAIL}", "-Dspring.mail.password=${PASSWORD}" ,"-jar", "roommate-application.jar"]