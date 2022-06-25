FROM openjdk:11
ADD target/voiceitProject-0.0.1-SNAPSHOT.jar voiceitProject-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "voiceitProject-0.0.1-SNAPSHOT.jar"]