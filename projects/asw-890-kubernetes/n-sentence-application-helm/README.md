# Sentence per Kubernetes (Helm) 

Questo sottoprogetto contiene il codice di specifica per rilasciare l'applicazione **sentence** su **Kubernetes** basato su **Helm**.  

## Ambiente di esecuzione 

Questa applicazione va eseguita nell'ambiente [kube-cluster](../../environments/kube-cluster/), sul nodo **kube-dev**. 
Vanno però utilizzate più finestre (terminali) diverse. In genere, una per l'applicazione e una per il suo client.  

## Configurazione 

La configurazione dell'applicazione (per es., il numero di repliche da utilizzare per i diversi servizi) 
può essere modificata in modo semplice agendo sul file [sentence/values.yaml](sentence/values.yaml). 

L'effettiva configurazione può essere visualizzata usando lo script `inspect-template.sh`.  

## Esecuzione 

Per avviare l'applicazione sul nodo **kube-dev**, eseguire lo script `deploy-sentence.sh`. 

Complessivamente, l'applicazione *sentence* espone un servizio REST sul cluster *kube-cluster* 
agli indirizzi **http://sentence.aswroma3.it** e **http://sentence.aswroma3.it:31080**, 
da cui è possibile ottenere una frase casuale.

In pratica, l'applicazione può essere verificata usando gli script `run-curl-client-ingress.sh` e `run-curl-client-nodeport.sh`. 

## Arresto 

Per arrestare l'applicazione, eseguire lo script `undeploy-sentence.sh` 

