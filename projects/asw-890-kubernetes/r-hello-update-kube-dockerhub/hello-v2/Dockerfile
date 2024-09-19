############################################
# Dockerfile per l'applicazione hello 
############################################

FROM eclipse-temurin:21-jdk 

ADD build/libs/hello-2.0.jar hello-2.0.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "hello-2.0.jar"]
