FROM openjdk:8
VOLUME /tmp
ADD build/libs/bb-rest-api-oauth2-0.0.1-SNAPSHOT.jar bb-rest-api-oauth2.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "bb-rest-api-oauth2.jar"]