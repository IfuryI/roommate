FROM openjdk:17-alpine
ADD roommate-application/target/roommate-application.jar roommate-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "roommate-application.jar"]