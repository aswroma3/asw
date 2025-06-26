# Hello per Kubernetes

Questo sottoprogetto contiene il codice di specifica per rilasciare l'applicazione **hello** su **Kubernetes**. 

## Ambiente di esecuzione 

Nodo **kube-dev** di un ambiente **kube-cluster**. 

## Esecuzione 

Per avviare l'applicazione sul nodo **kube-dev**, eseguire lo script `deploy-hello.sh`. 

Complessivamente, l'applicazione *hello* espone un servizio REST sul cluster *kube-cluster* all'indirizzo **http://hello** (**http://hello:80**), da cui è possibile ottenere un saluto.

In pratica, l'applicazione può essere verificata usando gli script `run-curl-client-ingress.sh` e `run-curl-client-nodeport.sh`. 

## Arresto 

Per arrestare l'applicazione, eseguire lo script `undeploy-hello.sh` 

