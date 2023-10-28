
FROM openjdk:11

CMD ["java", "-jar", "formationtunisair-0.0.1-SNAPSHOT.jar"]
COPY ./target/formationtunisair-0.0.1-SNAPSHOT.jar formationtunisair-0.0.1-SNAPSHOT.jar
