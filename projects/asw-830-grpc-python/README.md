# Invocazione remota con gRPC, versione per Python (asw-830-grpc-python)

Questo progetto contiene alcune applicazioni Python che esemplificano l'invocazione remota con **gRPC**. 

Le applicazioni sono definite in corrispondenza ad alcune delle applicazioni mostrate nel progetto [asw-830-grpc](../asw-830-grpc/): 

* **a-hello** è un esempio minimale di applicazione client-server basata su gRPC  

* **b-restaurants** è l'applicazione per la gestione di ristoranti 

Alcune osservazioni su queste applicazioni Python: 

* prima di eseguire ognuna delle applicazioni, è necessario compilare i relativi file **.proto** 
  eseguendo lo script `compile-proto-to-python.sh`

* l'implementazione delle funzionalità potrebbe essere semplificata rispetto alle versioni per Spring Boot 

* tuttavia, le diverse implementazioni tra loro dovrebbero essere compatibili; ad esempio: 
  * è possibile usare un server gRPC realizzato con Spring Boot e un client gRPC realizzato con Python 
  * in modo analogo, è possibile usare un server gRPC realizzato con Python e un client gRPC realizzato con Spring Boot 

* l'architettura delle applicazioni non è basata in modo rigoroso sull'architettura esagonale 

