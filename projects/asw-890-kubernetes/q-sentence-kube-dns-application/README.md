# Sentence (Kubernetes, usando il DNS al posto del servizio di service discovery)

Questo sottoprogetto contiene il codice di specifica per rilasciare su **Kubernetes** 
l'applicazione **sentence** basata sull'utilizzo del DNS di Kubernetes anziché del servizio di service dicovery. 

## Ambiente di esecuzione 

Questa applicazione va eseguita nell'ambiente [kube-cluster](../../environments/kube-cluster/), sul nodo **kube-dev**. 
Vanno però utilizzate più finestre (terminali) diverse. In genere, una per l'applicazione e una per il suo client.  

## Esecuzione 

Per avviare l'applicazione sul nodo **kube-dev**, eseguire lo script `deploy-sentence.sh` 
(oppure lo script `deploy-sentence-multi.sh` per avviare più repliche dei servizi per le parole). 

Complessivamente, l'applicazione *sentence* espone un servizio REST sul cluster *kube-cluster* 
agli indirizzi **http://sentence.aswroma3.it** e **http://sentence.aswroma3.it:31080**, 
da cui è possibile ottenere una frase casuale.

In pratica, l'applicazione può essere verificata usando gli script `run-curl-client-ingress.sh` e `run-curl-client-nodeport.sh`. 

## Esperimenti 

Confrontare il risultato dell'esecuzione di questa applicazione basata sul DNS 
con l'esecuzione dell'applicazione basata sul servizio di service discovery quando il servizio viene acceduto ripetutamente 
(ad esempio, con `run-curl-client-ingress.sh 100`). 
Nei due casi, osservare quali e quante risposte, e in che ordine temporale, 
provengono dalla versione sincrona e da quella asincrona del servizio *sentence*.  
Ripetere più volte questa osservazione. 

## Arresto 

Per arrestare l'applicazione, eseguire lo script `undeploy-sentence.sh`.

