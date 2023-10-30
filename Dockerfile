
FROM openjdk:11

CMD ["java", "-jar", "formationtunisair-1.0.0-SNAPSHOT.jar"]
COPY ./target/formationtunisair-1.0.0-SNAPSHOT.jar formationtunisair-1.0.0-SNAPSHOT.jar
