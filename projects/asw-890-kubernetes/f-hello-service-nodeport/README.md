# Hello - script per il rilascio con Kubernetes 

### Tipo di rilascio 

Service (con Cluster IP)  

### Ambiente di esecuzione 

Nodo **kube-dev** dell'ambiente [kube-cluster](../../environments/kube-cluster/). 

### Esecuzione 

* Avvio: eseguire lo script `deploy-hello.sh` dal nodo **kube-dev**

* Test: eseguire lo script `run-curl-client-service-nodeport.sh` dal nodo **kube-dev**

* Arresto: eseguire lo script `undeploy-hello.sh` dal nodo **kube-dev**


