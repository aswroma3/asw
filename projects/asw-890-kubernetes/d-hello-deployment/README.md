# Hello - script per il rilascio con Kubernetes 

### Tipo di rilascio 

Deployment  

### Ambiente di esecuzione 

Nodi **kube-dev** **kube-1** dell'ambiente [kube-cluster](../../environments/kube-cluster/). 

### Esecuzione 

* Avvio: eseguire lo script `deploy-hello.sh` dal nodo **kube-dev**

* Test: eseguire lo script `run-curl-client-deployment-dal-cluster.sh` dal nodo **kube-1**

* Arresto: eseguire lo script `undeploy-hello.sh` dal nodo **kube-dev**


