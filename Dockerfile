FROM openjdk:11
COPY ./target/formation-1.0.jar formation-1.0.jar
CMD ["java","-jar","formation-1.0.jar"]