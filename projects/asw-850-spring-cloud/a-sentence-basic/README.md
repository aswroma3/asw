# Sentence (basic)

Questo sottoprogetto mostra una versione di base dell'applicazione **sentence**.  

## Componenti eseguibili

Questa versione dell'applicazione **sentence** è formata dai seguenti componenti eseguibili: 

* **word-service** è il servizio per la generazione di parole casuali, che viene avviato con tre istanze: 
  * una con il profilo *subject* sulla porta *8081*, 
  * una con il profilo *verb* sulla porta *8082*, 
  * una con il profilo *object* sulla porta *8083* 
* **sentence-service** è il servizio per la generazione delle frasi casuali, sulla porta *8080*

## Esecuzione 

Per eseguire questa versione dell'applicazione: 

* eseguire lo script `run-sentence.sh`  

L'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client.sh N` per invocare il servizio *N* volte. 

Alla fine, l'applicazione può essere arrestata usando lo script `stop-sentence.sh` 
(**da usare con cautela, perché comunque questo script fa il `pkill` di un insieme di processi determinati sulla base di un'espressione regolare**). 
