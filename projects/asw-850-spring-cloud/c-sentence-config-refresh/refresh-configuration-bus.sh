#!/bin/bash

# effettua il refresh della configurazione (tramite Spring Cloud Bus) 

# curl -X POST http://localhost:8081/actuator/refresh
# curl -X POST http://localhost:8082/actuator/refresh
# curl -X POST http://localhost:8083/actuator/refresh

curl -X POST http://localhost:8081/actuator/busrefresh

# nota: bisogna aver configurato l'actuator e l'endpoint busrefresh 


