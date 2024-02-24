###########################################################
# Dockerfile per l'applicazione sentence / sentence service 
###########################################################

# FROM openjdk:17-jdk 
FROM eclipse-temurin:17-jdk 

ADD build/libs/sentence-async.jar sentence-async.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "sentence-async.jar"]

# HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1
