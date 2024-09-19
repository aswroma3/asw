#######################################################
# Dockerfile per l'applicazione sentence / word service 
#######################################################

FROM eclipse-temurin:21-jdk 

ADD build/libs/word.jar word.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "word.jar"]

# HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1

