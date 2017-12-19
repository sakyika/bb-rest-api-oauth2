FROM openjdk:8
ADD build/libs/bb-rest-api-oach2-0.0.1-SNAPSHOT.jar bb-rest-api-oath2.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "bb-rest-api-oath2.jar"]