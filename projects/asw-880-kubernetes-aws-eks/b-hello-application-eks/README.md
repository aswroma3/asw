# Hello per Amazon AWS EKS

Questo sottoprogetto contiene il codice di specifica per rilasciare l'applicazione **hello** su un cluster Amazon AWS EKS. 


## Ambiente di esecuzione 

Nodo ****aws-developer**. 


## Esecuzione 

Per avviare l'applicazione, eseguire lo script `deploy-hello.sh`. 

Si può monitorare l'applicazione mediante lo script `inspect-hello.sh`. 
In esecuzione ci dovrebbero essere due pod ed un ingress. 
L'applicazione può essere acceduta remotamente quando: 
* i pod sono tutti ready e 
* all'ingress controller dell'applicazione è stato assegnato un address. 

L'applicazione può essere verificata usando lo script `run-curl-client-ingress.sh`. 

Per arrestare l'applicazione, eseguire lo script `undeploy-hello.sh` 


## Discussione 

I file di risorse Kubernetes (i file .yaml) usati per rilasciare l'applicazione sul cluster Amazon AWS EKS 
sono esattamente gli stessi utilizzati per rilasciare l'applicazione nel cluster **kube-cluster**. 

Tuttavia, nel due casi, l'implementazione dello script `run-curl-client-ingress.sh` è leggermente diversa. 


