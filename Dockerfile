FROM openjdk:11
COPY target/formation-0.0.1.jar formation-0.0.1.jar
CMD ["java", "-jar", "formation-0.0.1.jar"]
