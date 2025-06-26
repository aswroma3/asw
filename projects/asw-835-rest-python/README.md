# Invocazione remota con REST, versione per Python (asw-835-rest-python)

Questo progetto contiene alcune applicazioni Python che esemplificano l'invocazione remota con **REST**. 

Le applicazioni sono definite in corrispondenza ad alcune delle applicazioni mostrate nel progetto [asw-835-rest](../asw-835-rest/): 

* **a-hello** � un esempio minimale di applicazione client-server basata su gRPC  

* **b-restaurants** � l'applicazione per la gestione di ristoranti 

Alcune osservazioni su queste applicazioni Python: 

* l'implementazione delle funzionalit� potrebbe essere semplificata rispetto alle versioni per Spring Boot 

* tuttavia, le diverse implementazioni tra loro dovrebbero essere compatibili; ad esempio: 
  * � possibile usare un server REST realizzato con Spring Boot e un client REST realizzato con Python 
  * in modo analogo, � possibile usare un server REST realizzato con Python e un client REST realizzato con Spring Boot 

* l'architettura delle applicazioni non � basata in modo rigoroso sull'architettura esagonale 

