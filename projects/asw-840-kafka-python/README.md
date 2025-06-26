# Comunicazione asincrona con Kafka, versione per Python (asw-840-kafka-python)

Questo progetto contiene alcune applicazioni Python che esemplificano la comunicazione asincrona con **Kafka**. 

Le applicazioni sono definite in corrispondenza ad alcune delle applicazioni mostrate nel progetto [asw-840-kafka](../asw-840-kafka/): 

* **a-simple-messaging** � una semplice applicazione con un produttore di messaggi e un consumatore di messaggi 
  
* **b-simple-pipeline** � una semplice applicazione con un produttore di messaggi, un filtro di messaggi e un consumatore di messaggi 
  
* **c-restaurants** � l'applicazione per la gestione di ristoranti 

Alcune osservazioni su queste applicazioni Python: 

* l'implementazione delle funzionalit� potrebbe essere semplificata rispetto alle versioni per Spring Boot 

* tuttavia, le diverse implementazioni tra loro dovrebbero essere compatibili; ad esempio: 
  * � possibile usare un produttore di messaggi Kafka realizzato con Spring Boot e un consumatore di messaggi Kafka realizzato con Python 
  * in modo analogo, � possibile usare un produttore di messaggi Kafka realizzato con Python e un consumatore di messaggi Kafka realizzato con Spring Boot 

* l'architettura delle applicazioni non � basata in modo rigoroso sull'architettura esagonale 

