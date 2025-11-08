# asw-840-kafka/c-restaurants

Questo progetto è l'applicazione per la gestione di ristoranti (già definita in un precedente progetto), con cui è ora possibile interagire anche in modo asincrono.


## Componenti eseguibili 

Questa applicazione è composta da tre componenti eseguibili: 

* **restaurant-server** è il servizio per la gestione dei ristoranti (con un'interfaccia web e REST)

* **restaurant-client-event-listener** è un ascoltatore di eventi relativi ai ristoranti, che effettua il log degli eventi pubblicati dal servizio dei ristoranti 

* **restaurant-client-command** è un produttore di comandi relativi ai ristoranti, che quando viene eseguito genera comandi per la creazione di due nuovi ristoranti 


## Ambiente di esecuzione 

Queste applicazioni vanno eseguite nel nodo **dev** di un ambiente **developer**. 
Vanno però utilizzate più finestre (terminali) diverse, una per ciascun componente eseguibile dell'applicazione. 


## Preparazione 

Preliminarmente all'esecuzione dell'applicazione è necessario avviare la sua infrastruttura (composta da **Kafka**, opportunamente configurato) come segue: 

* sul nodo **dev**, posizionarsi nella cartella principale di questo progetto `~/projects/asw-840-kafka/c-restaurants/`

* eseguire lo script `start-kafka-for-restaurant-service.sh`, che avvia Kafka e crea i canali per messaggi necessari per l'applicazione 


Inoltre, dopo aver eseguito l'applicazione è necessario arrestare **Kafka**, come segue: 

* sul nodo **dev**, posizionarsi nella cartella principale di questo progetto `~/projects/asw-840-kafka/c-restaurants/`

* eseguire lo script `stop-kafka.sh` 


## Esecuzione 

Per eseguire l'applicazione (dopo la *Preparazione*), vanno utilizzate tre (o più) finestre (terminali) diverse: una per il **server** del servizio dei ristoranti, una per l'**ascoltatore** di eventi e una per il **produttore** di comandi. 

Si proceda in questo modo: 

1. sulla finestra (terminale) nodo **event listener** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-840-kafka/c-restaurants/`

   b. eseguire il comando `gradle restaurant-event-listener:bootRun &` 
   
   c. l'ascoltatore di eventi può essere arrestato eseguendo lo script `stop-gradle-processes.sh`

2. sulla finestra (terminale) nodo **server** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-840-kafka/c-restaurants/`

   b. eseguire il comando `gradle restaurant-service:bootRun &` 
   
   c. il servizio può essere arrestato eseguendo lo script `stop-gradle-processes.sh`

3. sulla finestra **command publisher** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-840-kafka/c-restaurants/`

   b. eseguire il comando `gradle restaurant-command-publisher:bootRun` 

