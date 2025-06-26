# Esecuzione di applicazioni Spring con Docker (asw-875)

Questo progetto contiene alcune applicazioni che esemplificano il rilascio di applicazioni composte da uno o più servizi mediante container Docker. 

In particolare, le applicazioni mostrate sono varianti dell'applicazione **lucky-word** descritta nel progetto [asw-825-spring-boot/](../asw-825-spring-boot/) 
e dell'applicazione distribuita **sentence** descritta nel progetto [asw-850-spring-cloud/](../asw-850-spring-cloud/). 

* [a-lucky-word](a-lucky-word/): esemplifica l'esecuzione di una semplice applicazione web Spring Boot in un container Docker 

* [b-sentence-docker](b-sentence-docker/): per eseguire l'applicazione **sentence** come composizione di container, usando solo *Docker*

* [c-sentence-docker-compose](c-sentence-docker-compose/): per eseguire l'applicazione **sentence** come composizione di container, usando *Docker Compose*

**Le due versioni di quest'ultima applicazione utilizzano esattamente lo stesso codice per i progetti Java**. 
Tuttavia, nei due progetti cambia la modalità di gestione delle immagini e dei container Docker. 

## Ambiente di esecuzione 

Queste applicazioni vanno eseguite nel nodo **dev** di un ambiente **developer**. 
Può essere necessario utilizzare più finestre (terminali) diverse. In genere, una per l'applicazione e una per il suo client.  

