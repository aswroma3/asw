############################################
# Dockerfile per l'applicazione hello 
############################################

FROM eclipse-temurin:21-jdk 

ADD build/libs/hello-3.0.jar hello-3.0.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "hello-3.0.jar"]
