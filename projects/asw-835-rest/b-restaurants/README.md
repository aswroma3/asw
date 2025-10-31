# asw-835-rest/b-restaurants

Questo progetto � � l'applicazione per la gestione di ristoranti (gi� definita in un precedente progetto), con cui � ora possibile interagire anche in modo asincrono.


## Componenti eseguibili 

Questa applicazione � composta da due componenti eseguibili: 

* **restaurant-server** � il servizio per la gestione dei ristoranti (con un'interfaccia web e con un'interfaccia REST)

* **restaurant-client-rest** � un client REST per il servizio dei ristoranti 


## Ambiente di esecuzione 

Queste applicazioni vanno eseguite nel nodo **dev** di un ambiente **developer**. 
Vanno per� utilizzate pi� finestre (terminali) diverse, una per ciascun componente eseguibile dell'applicazione. 


## Esecuzione 

Per eseguire l'applicazione (dopo la sua costruzione), si proceda in questo modo: 

1. nel nodo **dev** di un ambiente **developer**, in una finestra (terminale) **server** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-830-grpc/b-restaurants/`

   b. eseguire il comando `gradle restaurant-service:bootRun &` oppure eseguire lo script `run-restaurant-service.sh`
   
   c. il servizio pu� essere arrestato eseguendo lo script `stop-gradle-processes.sh` 
      (**attenzione**: questo script arresta tutti i processi Gradle in esecuzione) 

2. nel nodo **dev** di un ambiente **developer**, in una finestra (terminale) **client** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-830-grpc/b-restaurants/`

   b. eseguire il comando `gradle restaurant-client-rest:bootRun` oppure eseguire lo script `run-restaurant-client-rest.sh`
   
   c. in alternativa, per utilizzare il client asincrono, 
      eseguire il comando `gradle restaurant-async-client-rest:bootRun` oppure eseguire lo script `run-restaurant-async-client-rest.sh`
