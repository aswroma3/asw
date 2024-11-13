# Python client per l'applicazione Sentence 

Questo sottoprogetto mostra un client REST concorrente Python per l'applicazioe Sentence, che può essere utilizzato con tutte le versioni dell'applicazione. 

## Esecuzione 

Per eseguire il client Python: 

* eseguire lo script `run-python-client.sh`, che effettua 10 chiamate concorrenti all'applicazione, intervallate da 500ms. 
  Lo script restituisce sia il risultato della chiamata, che il tempo impiegato per effettuare quella specifica chiamata 

* eseguire lo script `run-python-client.sh N` per effettuare N chiamate concorrenti all'applicazione, intervallate da 500ms  

* eseguire lo script `run-python-client.sh N DELAY` per effettuare N chiamate concorrenti all'applicazione, intervallate da un intervallo di DELAY ms   

