# Sentence (uso di tre circuit breaker separati per i tre servizi per le parole)

Questo sottoprogetto è una variante del sottoprogetto [g-sentence-circuit-breaker-retry](../g-sentence-circuit-breaker-retry/), 
che però usa tre circuit breaker separati per i tre servizi per le parole. 

## Differenze tra i due progetti 

* Nel progetto *g-sentence-circuit-breaker-retry* c'è un solo circuit breaker per tutti e 3 i tipi di parole. 
  Se anche uno solo di questi 3 servizi è problematico, l'unico circuit breaker si apre e non consente di raggiungere nemmeno i rimanenti 2 servizi funzionanti. 
 
* In questo progetto *h-sentence-independent-circuit-breakers* vengono usati 3 circuit breaker separati e indipendenti per i 3 tipi di parole. 
  Se uno solo di questi 3 servizi è problematico, si apre solo il suo circuit breaker, ed è possibile raggiungere i rimanenti 2 servizi funzionanti. 

