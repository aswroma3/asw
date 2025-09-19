# asw-825-spring-boot/d-restaurants

Questo progetto è l'applicazione web per la gestione di ristoranti, 
che sarà utilizzata poi come punto di partenza per definire ulteriori progetti. 


## Componenti eseguibili 

Questa applicazione è composta da un solo componente eseguibile: 

* il servizio per la gestione dei ristoranti (con un'interfaccia web)


## Ambiente di esecuzione 

Questa applicazione va eseguita nel nodo **dev** di un ambiente **developer**. 


## Esecuzione 

Per eseguire l'applicazione (dopo la sua costruzione), si proceda in questo modo: 

1. nel nodo **dev** di un ambiente **developer** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-825-spring-boot/c-restaurants/`

   b. eseguire il comando `gradle bootRun &` oppure eseguire lo script `run-restaurants-server.sh`
   
   c. il servizio può essere arrestato eseguendo lo script `stop-gradle-processes.sh` 
      (**attenzione**: questo script arresta tutti i processi Gradle in esecuzione) 
