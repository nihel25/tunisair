



FROM openjdk:11
COPY ./target/formationtunisair-0.0.1-SNAPSHOT.jar formationtunisair-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "formationtunisair-0.0.1-SNAPSHOT.jar"]