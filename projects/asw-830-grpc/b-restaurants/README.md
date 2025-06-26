# asw-830-grpc/b-restaurants

Questo progetto è è l'applicazione per la gestione di ristoranti (già definita in un precedente progetto), con cui è ora possibile interagire anche in modo asincrono.


## Componenti eseguibili 

Questa applicazione è composta da due componenti eseguibili: 

* **restaurant-server** è il servizio per la gestione dei ristoranti (con un'interfaccia web e con un'interfaccia gRPC)

* **restaurant-client-grpc** è un client gRPC per il servizio dei ristoranti 


## Ambiente di esecuzione 

Queste applicazioni vanno eseguite nel nodo **dev** di un ambiente **developer**. 
Vanno però utilizzate più finestre (terminali) diverse, una per ciascun componente eseguibile dell'applicazione. 


## Esecuzione 

Per eseguire l'applicazione (dopo la sua costruzione), si proceda in questo modo: 

1. nel nodo **dev** di un ambiente **developer**, in una finestra (terminale) **server** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-830-grpc/b-restaurants/`

   b. eseguire il comando `gradle restaurant-server:bootRun` oppure eseguire lo script `run-restaurant-server.sh`
   
   c. il servizio può essere arrestato con CTRL-C oppure eseguendo lo script `stop-gradle-processes.sh` 
      (**attenzione**: questo script arresta tutti i processi Gradle in esecuzione) 

2. nel nodo **dev** di un ambiente **developer**, in una finestra (terminale) **client** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-830-grpc/b-restaurants/`

   b. eseguire il comando `gradle restaurant-client-grpc:bootRun` oppure eseguire lo script `run-restaurant-client-grpc.sh`
   
   c. in alternativa, si può eseguire un semplice client di prova basato su [gRPCurl](https://github.com/fullstorydev/grpcurl)
      eseguendo lo script `run-restaurant-client-grpcurl.sh`

