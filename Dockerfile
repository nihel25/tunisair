FROM openjdk:11
ADD  target/formationtunisair-1.0.jar formationtunisair-1.0.jar
ENTRYPOINT ["java","-jar","formationtunisair-1.0.jar"]