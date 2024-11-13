# Sentence (con Consul e Spring Cloud Consul Discovery, Spring Cloud LoadBalancer e Spring Cloud Circuit Breaker/Resilience4J/Retry)

Questo sottoprogetto mostra una versione dell'applicazione **sentence** che, oltre al servizio di service discovery, un load balancer lato client, utilizza Circuit breaker e Retry (*Spring Cloud Circuit Breaker* con *Resilience4J*). 

I servizi *word-service* e *sentence-service* agiscono da client nei confronti del servizio di service discovery. 

Il servizio *sentence-service* usa i client REST con circuit breaker e/o retry basato su *Resilience4J* per accedere alle diverse istanze del servizio *word-service*. 

## Componenti eseguibili

Questa versione dell'applicazione **sentence** è formata dai seguenti componenti eseguibili: 

* **word-service** è il servizio per la generazione di parole casuali, che agisce da client nei confronti del servizio di service discovery, e che viene avviato con tre istanze: 
  * una con il profilo *subject* su una porta casuale, 
  * una con il profilo *verb* su una porta casuale, 
  * una con il profilo *object* su una porta casuale, 
* **sentence-service** è il servizio per la generazione delle frasi casuali, sulla porta *8080*, che agisce da client nei confronti dei servizi per le parole tramite il servizio di service discovery 

## Esecuzione 

Per eseguire questa versione dell'applicazione: 

* avviare *Consul* eseguendo lo script `start-consul.sh` 

* per avviare l'applicazione *sentence*, eseguire lo script `run-sentence.sh` 

L'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client.sh N` per invocare il servizio *N* volte.  

Alla fine, l'applicazione può essere arrestata usando lo script `stop-sentence.sh` 
(**da usare con cautela, perché comunque questo script fa il `pkill` di un insieme di processi determinati sulla base di un'espressione regolare**). 

Inoltre, *Consul* può essere arrestato con lo script `stop-consul.sh`. 

### Altre modalità di esecuzione 

* lo script `run-sentence-with-delays.sh` introduce un ritardo sia nel servizio delle parole che nel servizio delle frasi (le latenze si sommano)

* lo script `run-sentence-with-failures.sh` introduce la possibilità che uno o più servizi delle parole falliscano, con una certa probabilità (le parole mancanti vengono sostituite con degli asterischi) 

* lo script `run-sentence-with-heavy-failures.sh` introduce la possibilità che uno o più servizi delle parole falliscano (con una probabilità più alta) 

* lo script `run-sentence-replicated.sh` avvia due istanze del servizio *word-service* per ciascuno dei suoi tre profili (ma una sola istanza del servizio *sentence-service*)

* gli script `run-sentence-replicated-with-delays.sh`, `run-sentence-replicated-with-long-delays.sh`, `run-sentence-replicated-with-failures.sh` e `run-sentence-replicated-with-heavy-failures.sh` avviano più istanze dei servizi per le parole, con l'introduzione di ritardi e la possibilità di fallimenti 

### Esperimenti 

Durante l'esecuzione dell'applicazione, è possibile: 

* Uccidere gentilmente (usando il comando *kill -15*) il processo di uno o più dei servizi delle parole: 
  la frase generata potrà contenere una o più parole di *fallback*. 
  Per uccidere gentilmente un'istanza del servizio *subject* per il soggetto si può anche usare il comando `kill-a-java-process-soft.sh subject`.
  Eseguire questo esperimento, usando come client lo script `run-curl-client.sh 300`. 

* Uccidere brutalmente (usando il comando *kill -9*) il processo di uno o più dei servizi delle parole: 
  la frase generata potrà contenere una o più parole di *fallback*. 
  Per uccidere brutalmente un'istanza del servizio *subject* per il soggetto si può anche usare il comando `kill-a-java-process-hard.sh subject`.
  Eseguire questo esperimento, usando come client lo script `run-curl-client.sh 300`. 

### Esperimenti 

* Avviare l'applicazione con il comando `run-sentence-with-delays.sh` oppure `run-sentence-replicated-with-delays.sh`. 
  Ogni servizio introduce un ritardo di 50 millisecondi. 
  Eseguire come client lo script `run-curl-client.sh 300` per misurare la latenza per il calcolo di una frase. 

* Modificare gli script `run-sentence-with-delays.sh` e `run-sentence-replicated-with-delays.sh` in modo che ciascun servizio introduca un ritardo di 500 millisecondi o di 1000 millisecondi. 
  Eseguire come client lo script `run-curl-client.sh 300` per misurare la latenza per il calcolo di una frase. 

* Avviare l'applicazione con il comando `run-sentence-with-failures.sh` oppure `run-sentence-replicated-with-failues.sh`. 
  Eseguire come client lo script `run-curl-client.sh 300` per osservare l'effetto dei fallimenti nei servizi delle parole. 

* Avviare l'applicazione con il comando `run-sentence-with-heavy-failures.sh` oppure `run-sentence-replicated-with-heavy-failues.sh`. 
  Eseguire come client lo script `run-curl-client.sh 300` per osservare l'effetto dei fallimenti nei servizi delle parole. 

* Modificare gli script `run-sentence-with-failures.sh` e `run-sentence-replicated-with-failures.sh` per cambiare la probabilità di fallimento dei diversi servizi. 
  Eseguire come client lo script `run-curl-client.sh 300` per osservare l'effetto dei fallimenti nei servizi delle parole. 
