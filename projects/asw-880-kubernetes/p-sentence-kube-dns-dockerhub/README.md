# Sentence (Kubernetes, usando il DNS al posto del servizio di service discovery)

Questo sottoprogetto contiene il codice sorgente per l'applicazione **sentence** da eseguire su **Kubernetes** 
basata sull'utilizzo del DNS di Kubernetes anziché del servizio di service dicovery. 
Inoltre contiene gli script per il rilascio su **Docker Hub**. 

Questa versione differisce dalla [versione basata sul servizio di service discovery](../l-sentence-kube-dockerhub/) 
per i seguenti motivi: 

* non viene utilizzato il servizio di *service discovery* di Kubernetes ma la comunicazione tra servizi è basata sul *DNS* di Kubernetes 


## Componenti eseguibili

Questa versione dell'applicazione **sentence** è formata dai seguenti componenti: 

* **word-service** è il servizio per la generazione di parole casuali
* **sentence-service** è il servizio per la generazione delle frasi casuali
* **sentence-service-async** è il servizio per la generazione delle frasi casuali, che interagisce in modo asincrono con i servizi per le parole 
* **api-gateway** è l'API gateway per l'applicazione **sentence** 


## Build Java - OPZIONALE 

*Questo passo è necessario solo se si vogliono utilizzare delle immagini Docker diverse da quelle predisposte dal docente del corso.*

Per la costruzione dell'applicazione, eseguire il comando `gradle assemble` oppure `gradle build`


## Build (Docker) - OPZIONALE 

*Questo passo è necessario solo se si vogliono utilizzare delle immagini Docker diverse da quelle predisposte dal docente del corso.*

Per la costruzione delle immagini Docker ed effettuare il push su Docker Hub: 

* accedere a Docker Hub, eseguendo il comando `docker login` (è necessaria la registrazione a Docker Hub)

* modificare il file `docker-compose.yml` (e i file Kubernetes nei sottoprogetti successivi), usando il nome del proprio account su Docker Hub al posto di **aswroma3** 

* eseguire lo script `build-and-push-docker-image.sh` 


## Ambiente di esecuzione 

Queste attività possono essere eseguite nel nodo **kube-dev** di un ambiente **kube-cluster**. 
