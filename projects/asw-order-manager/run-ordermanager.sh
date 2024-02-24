#!/bin/bash

# Script per avviare l'applicazione OrderManager 

echo Running ORDERMANAGER 

# Consul deve essere avviato separatamente 

java -Xms64m -Xmx128m -jar order-service/build/libs/order-service.jar &
java -Xms64m -Xmx128m -jar product-service/build/libs/product-service.jar &
java -Xms64m -Xmx128m -jar order-validation-service/build/libs/order-validation-service.jar &

java -Xms64m -Xmx128m -jar api-gateway/build/libs/api-gateway.jar &
