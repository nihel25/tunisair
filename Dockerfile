



FROM openjdk:11
COPY ./target/formationtunisair-0.0.1.jar formationtunisair-0.0.1.jar
CMD ["java", "-jar", "formationtunisair-0.0.1.jar"]