# asw-840-kafka/b-simple-pipeline

Questo progetto è una semplice applicazione con un produttore di messaggi, un filtro di messaggi e un consumatore di messaggi.


## Componenti eseguibili 

Questa applicazione è composta da tre componenti eseguibili: 

* **simple-producer** è un componente produttore di messaggi 

* **simple-filter** è un componente filtro di messaggi 

* **simple-consumer** è un componente consumatore di messaggi 


## Ambiente di esecuzione 

Queste applicazioni vanno eseguite nel nodo **dev** di un ambiente **developer**. 
Vanno però utilizzate più finestre (terminali) diverse, una per ciascun componente eseguibile dell'applicazione. 


## Preparazione 

Preliminarmente all'esecuzione dell'applicazione è necessario avviare **Kafka**, come segue: 

* nel nodo **dev**, posizionarsi nella cartella principale di questo progetto `~/projects/asw-840-kafka/b-simple-pipeline/` 

* eseguire lo script `start-kafka-for-simple-messaging.sh`, che avvia *Kafka* ed inoltre crea i topic *asw-alpha*, *asw-beta* e *asw-gamma* 


Inoltre, dopo aver eseguito l'applicazione è necessario arrestare **Kafka**, come segue: 

* nel nodo **dev**, posizionarsi nella cartella principale di questo progetto `~/projects/asw-840-kafka/b-simple-pipeline/` 

* eseguire lo script `stop-kafka.sh` 


## Esecuzione 

Per eseguire l'applicazione (dopo la *Preparazione*), vanno utilizzate tre (o più) finestre (terminali) diverse: 
una per il **consumatore**, una per il **filtro** e una per il **produttore**. 

Si proceda in questo modo: 

1. sulla finestra (terminale) nodo **consumatore** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-840-kafka/b-simple-pipeline`

   b. eseguire il comando `gradle simple-consumer:bootRun` 
   
   c. il consumatore può essere poi arrestato con CTRL-C oppure eseguendo lo script `stop-gradle-processes.sh`

2. sulla finestra (terminale) nodo **filtro** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-840-kafka/b-simple-pipeline`

   b. eseguire il comando `gradle simple-filter:bootRun` 
   
   c. il filtro può essere arrestato con CTRL-C oppure eseguendo lo script `stop-gradle-processes.sh`

3. sulla finestra **produttore** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-840-kafka/b-simple-pipeline`

   b. eseguire il comando `gradle simple-producer:bootRun` 

