# Sentence per Kubernetes

Questo sottoprogetto contiene il codice di specifica per rilasciare l'applicazione **sentence** su **Kubernetes**. 

## Ambiente di esecuzione 

Nodo **kube-dev** di un ambiente **kube-cluster**. 

## Esecuzione 

Per avviare l'applicazione sul nodo **kube-dev**, eseguire lo script `deploy-sentence.sh` 
(oppure lo script `deploy-sentence-multi.sh` per avviare più repliche dei servizi per le parole). 

Complessivamente, l'applicazione *sentence* espone un servizio REST sul cluster *kube-cluster* 
agli indirizzi **http://sentence.aswroma3.it** e **http://sentence.aswroma3.it:31080**, 
da cui è possibile ottenere una frase casuale.

In pratica, l'applicazione può essere verificata usando gli script `run-curl-client-ingress.sh` e `run-curl-client-nodeport.sh`, 
nonché gli script `run-python-client-ingress.sh` e `run-python-client-nodeport.sh`. 

## Arresto 

Per arrestare l'applicazione, eseguire lo script `undeploy-sentence.sh`.

