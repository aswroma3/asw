# asw-840-kafka/a-simple-messaging

Questo progetto è una semplice applicazione con un produttore di messaggi e un consumatore di messaggi.


## Componenti eseguibili 

Questa applicazione è composta da due componenti eseguibili: 

* **simple-producer** è un componente produttore di messaggi 

* **simple-consumer** è un componente consumatore di messaggi 


## Ambiente di esecuzione 

Queste applicazioni vanno eseguite nel nodo **dev** di un ambiente **developer**. 
Vanno però utilizzate più finestre (terminali) diverse, una per ciascun componente eseguibile dell'applicazione. 


## Preparazione 

Preliminarmente all'esecuzione dell'applicazione è necessario avviare **Kafka**, come segue: 

* nel nodo **dev**, posizionarsi nella cartella principale del progetto `~/projects/asw-840-kafka/` 

* eseguire lo script `start-kafka.sh` 

* eseguire lo script `create-some-kafka-topics.sh`, che crea i topic *asw-alpha*, *asw-beta* e *asw-gamma* 


Inoltre, dopo aver eseguito l'applicazione è necessario arrestare **Kafka**, come segue: 

* nel nodo **dev**, posizionarsi nella cartella principale del progetto `~/projects/asw-840-kafka/` 

* eseguire lo script `stop-kafka.sh` 


## Esecuzione 

Per eseguire l'applicazione (dopo la *Preparazione*), vanno utilizzate due (o più) finestre (terminali) diverse: una per il **consumatore** e una per il **produttore**. 

Si proceda in questo modo: 

1. sulla finestra (terminale) nodo **consumatore** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-840-kafka/a-simple-messaging`

   b. eseguire il comando `gradle simple-consumer:bootRun` 
   
   c. il consumatore può essere arrestato con CTRL-C 

2. sulla finestra **produttore** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-840-kafka/a-simple-messaging`

   b. eseguire il comando `gradle simple-producer:bootRun` 

Inoltre: 

* se si vuole usare un altro gruppo per il consumatore (il default è *simple-consumer*), 
  avviare il consumatore con il comando `ASW_KAFKA_GROUPID=anothergroup gradle simple-consumer:bootRun`

* se si vuole far ricevere i messaggi al consumatore da un altro canale (il default è *asw-alpha*), 
  avviare il consumatore con il comando `ASW_KAFKA_CHANNEL_IN=asw-beta gradle simple-consumer:bootRun`

* se si vuole far inviare i messaggi al produttore a un altro canale (il default è *asw-alpha*), 
  avviare il produttore con il comando `ASW_KAFKA_CHANNEL_OUT=asw-beta gradle simple-producer:bootRun`

* se si vuole far inviare un certo numero di messaggi (il default è *20*), 
  avviare il produttore con il comando `ASW_KAFKA_PRODUCER_MESSAGES_TO_SEND=100 gradle simple-producer:bootRun` 
  (usare *0* per inviare un numero infinito di messaggi)

* se in un nuovo gruppo si si vogliono leggere anche i messaggi precedenti (il default è *latest*), 
  avviare il consumatore con il comando `SPRING_KAFKA_CONSUMER_AUTO_OFFSET_RESET=earliest ASW_KAFKA_GROUPID=anothergroup gradle simple-consumer:bootRun`

* per cambiare il nome del produttore nei messaggi (il default è *Producer*), 
  avviare il produttore con il comando `ASW_KAFKA_PRODUCER_NAME=anotherproducer gradle simple-producer:bootRun`

* per cambiare il nome del consumatore nei messaggi (il default è *Consumer*), 
  avviare il consumatore con il comando `ASW_KAFKA_CONSUMER_NAME=anotherconsumer gradle simple-consumer:bootRun`
