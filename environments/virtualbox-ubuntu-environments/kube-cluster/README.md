# Kubernetes (kube-cluster) (versione per VirtualBox basata su Ubuntu)

Questo ambiente di esecuzione è composto da un cluster di tre macchine virtuali [Kubernetes](https://kubernetes.io/), 
realizzato con [Kubeadm](https://kubernetes.io/docs/reference/setup-tools/kubeadm/), 
più una macchina virtuale per la compilazione dei progetti e per la costruzione delle immagini dei container *Docker*. 

L'ambiente è composto da quattro macchine virtuali

* le macchine virtuali **kube-1**, **kube-2** e **kube-3** per il cluster *Kubernetes* realizzato con Kubeadm

* la macchina virtuale **kube-dev** per lo sviluppatore 


## Descrizione delle macchine virtuali 

### kube-1, kube-2, kube-3

Le macchine virtuali **kube-1**, **kube-2** e **kube-3** per il cluster *Kubernetes* hanno il seguente software 

* Ubuntu 24.04 LTS a 64 bit (by Bento) 

* Containerd come container engine  

* Kubernetes (Kubeadm) 

* Add on di Kubernetes: Calico Network, NGINX Ingress 

La macchina virtuale **kube-1** è configurata come *control plane*, mentre le macchine virtuali **kube-2** e **kube-3** sono configurate come *nodi worker*. 

Configurazione di rete 

* Indirizzi IP: 10.11.1.71, 10.11.1.72 e 10.11.1.73 (rispettivamente) delle tre VM 

* Porte pubblicate sull'host: 
  * la porta 31080 (http dell'ingress controller) è pobblicata sulle porte 31081, 31082, 31083 (rispettivamente) delle tre VM 
  
* Alias delle VM del cluster registrati in */etc/hosts*
  * *kube-cluster*: tutti e tre le macchine virtuali, *kube-1*, *kube-2* e *kube-3*
  * *kube-control-plane*: le macchine virtuali per il control plane, *kube-1*
  * *kube-node*: le macchine virtuali per i nodi worker, *kube-2* e *kube-3*

Hardware (virtuale) 

* Memoria: 3072 MB (3 GB) - se necessario, si può ridurre a 2048 (2 GB) modificando il Vagrantfile 

* Virtual CPU: 3 - se necessario, si può ridurre a 2 modificando il Vagrantfile 

* CPU execution cap: 100% (kube-1), 100% (kube-2 e kube-3) 

### kube-dev

La macchina virtuale **kube-dev** ha il seguente software 

* Ubuntu 24.04 LTS a 64 bit (by Bento) 

* Java SDK (Open JDK), Gradle e Maven 

* Docker e Docker Compose 

* Client Kubernetes (kubectl) 

* Helm 

* Python 

Configurazione di rete 

* Indirizzo IP: 10.11.1.79 

* Porte pubblicate sull'host: nessuna <!--  8080 (http) -> 8089  -->  

* Alias delle VM del cluster registrati in */etc/hosts*
  * *kube-cluster*: tutti e tre le macchine virtuali, *kube-1*, *kube-2* e *kube-3*
  * *kube-control-plane*: le macchine virtuali per il control plane, *kube-1*
  * *kube-node*: le macchine virtuali per i nodi worker, *kube-2* e *kube-3*
  * *kube-dev*: per la macchina virtuale *kube-dev*

Hardware (virtuale) 

* Memoria: 2048 MB (2 GB)  

* Virtual CPU: 2 

* CPU execution cap: 100% 


## Tempo di preparazione dell'ambiente 

Tempo di primo provisioning dell'ambiente: circa 15-40 minuti 

