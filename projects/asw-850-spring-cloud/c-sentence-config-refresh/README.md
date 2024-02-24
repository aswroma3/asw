# Sentence (con Spring Cloud Config e Spring Cloud Config Bus)

Questo sottoprogetto mostra una versione dell'applicazione **sentence** che utilizza un configuration server (*Spring Cloud Config*), con refresh della configurazione 

## Componenti eseguibili

Questa versione dell'applicazione **sentence** è formata dai seguenti componenti eseguibili: . 

* **config-server** è un configuration server (Spring Cloud Config), sulla porta *8888*, che definisce le parole casuali per il servizio *word-service*
* **word-service** è il servizio per la generazione di parole casuali, che agisce da client per il configuration server, e che viene avviato con tre istanze: 
  * una con il profilo *subject* sulla porta *8081*, 
  * una con il profilo *verb* sulla porta *8082*, 
  * una con il profilo *object* sulla porta *8083* 
* **sentence-service** è il servizio per la generazione delle frasi casuali, sulla porta *8080*

## Esecuzione 

Per eseguire questa versione dell'applicazione: 

* avviare il message broker RabbitMQ, eseguendo lo script `start-rabbitmq.sh`

* per avviare il configuration server, eseguire lo script `run-config-server-local.sh` (usa la configurazione nei file locali) oppure lo script `run-config-server-remote.sh` (usa la configurazione su Git)

* per avviare l'applicazione *sentence*, eseguire lo script `run-sentence.sh` 

L'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client.sh N` per invocare il servizio *N* volte.  

Alla fine: 

* l'applicazione può essere arrestata usando lo script `terminate-java-processes.sh` (**da usare con cautela!**) 

* il solo configuration server può invece essere arrestato usando lo script `kill-config-server.sh` (**da usare con cautela!**)

* il message broker RabbitMQ può essere arrestato con lo script `stop-rabbitmq.sh`
 

## Esperimenti 

### Esecuzione senza configuration server 

Se non viene avviato il configuration server ma solo l'applicazione *sentence*, allora l'applicazione usa la configurazione definita nei file di configurazione locali all'applicazione *sentence*. 

### Refresh della configurazione  

* avviare il message broker RabbitMQ, eseguendo lo script `start-rabbitmq.sh`

* non avviare il configuration server 

* avviare l'applicazione *sentence*, eseguendo lo script `run-sentence.sh` 

* eseguire lo script `run-curl-client.sh`: la frase casuale generata è *default default default* (l'applicazione usa la configurazione definita nei file di configurazione locali all'applicazione)

* avviare il configuration server, eseguendo lo script `run-config-server-local.sh` (usa la configurazione nei file locali del configuration server) 

* eseguire lo script `run-curl-client.sh`: la frase casuale generata è ancora *default default default* (l'applicazione usa la configurazione definita nei file di configurazione locali all'applicazione)

* effettuare il refresh dell'applicazione *sentence*, eseguendo lo script `refresh-configuration.sh` 

* eseguire lo script `run-curl-client.sh`: la frase casuale generata ora è *Spring Cloud Config works from local files* (l'applicazione usa la configurazione definita localmente nel servizio *config-server*)

* arrestare il configuration server in esecuzione (usando lo script `kill-config-server.sh`)  

* avviare il configuration server, eseguendo lo script `run-config-server-remote.sh` (usa la configurazione remota su GitHub) 

* eseguire lo script `run-curl-client.sh`: la frase casuale generata è ancora *Spring Cloud Config works from local files* 

* effettuare il refresh dell'applicazione *sentence* in modo dinamico, eseguendo lo script `refresh-configuration-bus.sh` 

* eseguire lo script `run-curl-client.sh`: la frase casuale generata ora è *SPRING CLOUD CONFIG WORKS FROM REMOTE FILES* (l'applicazione usa la configurazione remota su GitHub)
