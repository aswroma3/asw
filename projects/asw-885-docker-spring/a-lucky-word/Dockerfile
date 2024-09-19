############################################
# Dockerfile per l'applicazione lucky-word 
############################################

FROM eclipse-temurin:21-jdk 

ADD build/libs/lucky-word.jar lucky-word.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "lucky-word.jar"]
