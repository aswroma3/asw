# Sentence - guida alla configurazione

La configurazione viene introdotta a partire dal progetto *asw-850-spring-cloud/d-sentence-service-discovery*, 
e poi utilizzata, sempre uguale, anche nei progetti successivi. 


## Word Service 

### Configurazione di dominio 

La configurazione di dominio del servizio *word* è formata dalle seguenti proprietà e profili: 

* la proprietà *asw.sentence.wordservice.words*: le parole causali tra cui scegliere, separate da virgola 

* i profili *subject*, *verb* e *object* 

### Altre proprietà 

Il servizio *word* utilizza le seguenti proprietà aggiuntive, usate nel controller REST e per effettuare il logging:  

* asw.sentence.wordservice.instancename nome dell'istanza 

* asw.sentence.wordservice.delay latenza (esatta) da introdurre nell'accesso a una parola  

* asw.sentence.wordservice.failurerate tasso di fallimento, ovvero probabilità con cui genera un'eccezione HTTP  

### Logging

Il servizio restituisce solo la parola scelta. 

Normalmente il log mostra solo la parola scelta. 

Ma se è settato il nome dell'istanza, il log mostra anche il nome dell'istanza, la latenza e il tasso di fallimento 


## Sentence Service 

### Configurazione di dominio 

Il servizio *sentence* non prevede nessuna configurazione di dominio (le frasi hanno sempre la stessa forma) 

### Altre proprietà 

I servizi *sentence* e *sentence-async* utilizzano le seguenti proprietà aggiuntive, usate nel controller REST e per effettuare il logging:  

* asw.sentence.sentenceservice.instancename nome dell'istanza 

* asw.sentence.sentenceservice.delay latenza aggiuntiva (esatta) da introdurre nell'accesso a una frase  

### Logging

Normalmente il servizio restituisce solo la frase scelta, e la mostra nel log.  

Tuttavia, se è settato il nome dell'istanza, la frase scelta include anche il nome dell'istanza, la latenza aggiuntiva e il tempo di esecuzione complessivo  

