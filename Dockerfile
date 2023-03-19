FROM openjdk:17-alpine

ARG EMAIL
ARG PASSWORD

ENV EMAIL_VAR=$EMAIL
ENV PASSWORD_VAR=$PASSWORD

ADD roommate-application/target/roommate-application.jar roommate-application.jar

EXPOSE 8080


ENTRYPOINT ["java", "-Dspring.mail.username=${EMAIL_VAR}", "-Dspring.mail.password=${PASSWORD_VAR}" ,"-jar", "roommate-application.jar"]