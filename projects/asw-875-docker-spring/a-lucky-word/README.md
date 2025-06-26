# Lucky word

Questo sottoprogetto esemplifica come eseguire un'applicazione web Spring Boot in un container Docker. 

## Esecuzione 

Ci sono due modi per eseguire questa applicazione: 

* costruire l'immagine Docker localmente e poi creare e avviare un container Docker da questa immagine locale 

* poi creare e avviare un container Docker da un'immagine su Docker Hub 

Esaminiamo le due modalità separatamente. 


### Esecuzione con immagine Docker locale 

Per prima cosa, è necessario effettuare la build (Java) dell'applicazione con Gradle,  
nell'ambiente [workstation](../../../environments/workstation/), 
sempre essendo posizionati nella cartella del sottoprogetto: 

* eseguire `gradle build` nella cartella del sottoprogetto 

Poi bisogna costruire e mandare in esecuzione l'applicazione in un container Docker, 
operando sempre nell'ambiente [workstation](../../../environments/workstation/) 
e sempre essendo posizionati nella cartella del sotto progetto: 

* eseguire il comando `docker image build --rm -t lucky-word .` 
 
* eseguire il comando `docker container run -d -p 8080:8080 --name=lucky-word lucky-word` 
  
* in alternativa, eseguire il comando `docker container run -d -p 8080:8080 --name=lucky-word -e SPRING_PROFILES_ACTIVE=english lucky-word` 
  oppure il comando `docker container run -d -p 8080:8080 --name=lucky-word -e SPRING_PROFILES_ACTIVE=italian lucky-word` 
  per utilizzare un profilo differente 

Il container espone il suo servizio sulla porta `8080`, 
che è anche collegata alla porta `8080` della macchina virtuale **workstation**, 
sul path `/lucky-word`. 
Pertanto, il servizio sarà accessibile nella macchina virtuale **workstation** 
all'indirizzo `localhost:8080/lucky-word` 
(si veda lo script `run-curl-client.sh`). 

Sull'host, potrebbe essere accessibile ad una porta diversa, 
in genere sulla porta `8080` o sulla porta `8081` (vedere il port forwarding dell'ambiente). 


### Esecuzione con immagine Docker da Docker Hub  

In questo caso, non è necessario è necessario effettuare la build (Java) dell'applicazione. 
Anzi, non è nemmeno necessario il codice sorgente dell'applicazione. 

Poi invece mandare in esecuzione l'applicazione in un container Docker, 
operando nell'ambiente [workstation](../../../environments/workstation/),  
posizionati nella cartella del sotto progetto: 

* eseguire il comando `docker container run -d -p 8080:8080 --name=lucky-word aswroma3/lucky-word` 
  oppure lo script `run-lucky-word.sh`
  
* in alternativa, eseguire il comando `docker container run -d -p 8080:8080 --name=lucky-word -e SPRING_PROFILES_ACTIVE=english aswroma3/lucky-word` 
  oppure il comando `docker container run -d -p 8080:8080 --name=lucky-word -e SPRING_PROFILES_ACTIVE=italian aswroma3/lucky-word` 
  oppure gli script `run-lucky-word-as-english.sh` o `run-lucky-word-as-italian.sh`
  per utilizzare un profilo differente 

Il container espone il suo servizio sulla porta `8080`, 
che è anche collegata alla porta `8080` della macchina virtuale **workstation**, 
sul path `/lucky-word`. 
Pertanto, il servizio sarà accessibile nella macchina virtuale **workstation** 
all'indirizzo `localhost:8080/lucky-word` 
(si veda lo script `run-curl-client.sh`). 

Sull'host, potrebbe essere accessibile ad una porta diversa, 
in genere sulla porta `8080` o sulla porta `8081` (vedere il port forwarding dell'ambiente). 