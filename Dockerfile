FROM openjdk:11
COPY ./target/formationtunisair-1.0.jar formationtunisair-1.0.jar
CMD ["java","-jar","formation-1.0.jar"]