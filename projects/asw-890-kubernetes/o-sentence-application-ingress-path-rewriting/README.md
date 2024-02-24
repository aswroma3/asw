# Sentence per Kubernetes

Questo sottoprogetto propone degli esperimenti relativi all'ingress controller, 
ed in particolare alle sue funzionalità di routing delle richieste e di path rewriting. 

## Esperimenti 

L'ingress controller definisce alcuni path di accesso all'applicazione **sentence**, come segue. 

* **http://sentence.aswroma3.it** - l'applicazione **sentence** acceduta mediante l'api gateway. 
  Si può verificare con lo script `run-curl-client.sh`. 
  L'api gateway consente l'accesso anche alle singole parti delle frasi, ad esempio **http://sentence.aswroma3.it/subject**. 
  Si può verificare con lo script `run-curl-client-word.sh`. 

* **http://sentence.aswroma3.it/sentence** - accesso diretto al servizio delle frasi, bypassando l'api gateway. 
  Si può verificare con lo script `run-curl-client-sentence.sh`. 

* **http://sentence.aswroma3.it/sentence/sync** - accesso diretto alle frasi generate dal solo servizio che accede ai servizi delle parole in modo *sincrono*. 
  Si può verificare con lo script `run-curl-client-sentence-sync.sh`. 

* **http://sentence.aswroma3.it/sentence/async** - accesso diretto alle frasi generate dal solo servizio che accede ai servizi delle parole in modo *asincrono*. 
  Si può verificare con lo script `run-curl-client-sentence-async.sh`. 
