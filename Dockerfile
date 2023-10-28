FROM openjdk:11
COPY ./target/formationtunisair-1.0.jar formationtunisair-1.0.jar
CMD ["java","-jar","formationtunisair-1.0.jar"]