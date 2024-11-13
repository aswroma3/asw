# Sentence (con Spring Cloud Config)

Questo sottoprogetto mostra una versione dell'applicazione **sentence** che utilizza un configuration server (*Spring Cloud Config*). 

## Componenti eseguibili

Questa versione dell'applicazione **sentence** è formata dai seguenti componenti eseguibili: 

* **config-server** è un configuration server (Spring Cloud Config), sulla porta *8888*, che definisce le parole casuali per il servizio *word-service*
* **word-service** è il servizio per la generazione di parole casuali, che agisce da client per il configuration server, e che viene avviato con tre istanze: 
  * una con il profilo *subject* sulla porta *8081*, 
  * una con il profilo *verb* sulla porta *8082*, 
  * una con il profilo *object* sulla porta *8083* 
* **sentence-service** è il servizio per la generazione delle frasi casuali, sulla porta *8080*

## Esecuzione 

Per eseguire questa versione dell'applicazione: 

* per avviare il configuration server, eseguire lo script `run-config-server-local.sh` (usa la configurazione nei file locali del configuration server) oppure lo script `run-config-server-remote.sh` (usa la configurazione su Git)

* se non viene avviato il configuration server, allora viene usata la configurazione di default dell'applicazione 

* per avviare l'applicazione *sentence*, eseguire lo script `run-sentence.sh` 

L'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client.sh N` per invocare il servizio *N* volte.  

Alla fine, l'applicazione può essere arrestata usando lo script `stop-sentence.sh` 
(**da usare con cautela, perché comunque questo script fa il `pkill` di un insieme di processi determinati sulla base di un'espressione regolare**). 

Il solo configuration server può invece essere arrestato usando lo script `kill-config-server.sh` (**da usare con cautela!**). 

## Esperimenti 

### Esecuzione senza configuration server 

Se non viene avviato il configuration server ma solo l'applicazione *sentence*, 
allora l'applicazione usa la configurazione definita nei file di configurazione locali all'applicazione *sentence*. 
