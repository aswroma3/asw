# Architettura dei Sistemi Software a Roma Tre (2024-2025)

Benvenuti al repository del corso 
di [Architettura dei Sistemi Software](https://asw.inf.uniroma3.it/) a Roma Tre, 
edizione 2024-2025 (A.A. 2024-2025), 
tenuto dal prof. [Luca Cabibbo](https://cabibbo.inf.uniroma3.it/). 

Per la versione del repository relativa alla precedente edizione del corso, si faccia invece riferimento al branch **asw-2023-2024** di questo repository. 

Questo repository contiene il codice delle *esercitazioni* 
del corso di [Architettura dei Sistemi Software](https://asw.inf.uniroma3.it/), 
che sono relative a delle semplici *applicazioni software distribuite* 
(basate sull'uso di *middleware*), 
che vanno eseguite in degli opportuni *ambienti distribuiti*: 
* il software è normalmente scritto in [Java](http://www.oracle.com/technetwork/java/index.html), 
  e costruito con [Gradle](http://gradle.org/); 
* ciascun ambiente di esecuzione distribuito è composto da una o più macchine virtuali create con 
  [VirtualBox](https://www.virtualbox.org/) oppure [VMware Workstation](https://www.vmware.com/products/desktop-hypervisor/workstation-and-fusion)
  e [Vagrant](https://www.vagrantup.com/), 
  e accedute tramite [Git](https://git-scm.com/); 
* inoltre, alcuni ambienti di esecuzione sono basati sui container [Docker](https://www.docker.com/)
  e sull'orchestrazione di container con [Kubernetes](https://kubernetes.io/)

## Software da installare sul proprio PC 

### Software per la gestione degli ambienti di esecuzione  

Ecco il software utilizzato dal docente per la gestione degli ambienti di esecuzione con *Windows 11 Pro (versione 24H2)*. 

* [Vagrant](https://www.vagrantup.com/), versione 2.4.7
* [Git](https://git-scm.com/), versione 2.47.0.2  
* opzionalmente [Docker](https://www.docker.com/), 
  che però non è strettamente necessario, poiché può essere eseguito nelle macchine virtuali
* almeno un provider di virtualizzazione scelto tra VirtualBox e VMware Workstation, come descritto qui di seguito 

Ecco il software aggiuntivo richiesto per l'utilizzo di VMware Workstation. 

* [VMware Workstation Pro](https://www.vmware.com/products/desktop-hypervisor/workstation-and-fusion), versione 17.6.3
* [Vagrant VMware Utility](https://developer.hashicorp.com/vagrant/docs/providers/vmware/vagrant-vmware-utility), versione 1.0.24
* [Vagrant VMware Plugin](https://developer.hashicorp.com/vagrant/docs/providers/vmware/installation)
  
E' inoltre necessario essere consapevoli 
di alcune [limitazioni del supporto di Vagrant per VMware Workstation](https://developer.hashicorp.com/vagrant/docs/providers/vmware/known-issues), 
ed in particolare la possibilità che, durante l'avvio con Vagrant di macchine virtuali VMware, 
altre macchine virtuali VMware perdano temporaneamente la connettività di rete.  

Ecco il software aggiuntivo richiesto per l'utilizzo di VirtualBox. 

* [VirtualBox](https://www.virtualbox.org/), versione 7.1.10 
  
E' inoltre necessario essere consapevoli 
di alcune [problematiche del supporto di Vagrant per VirtualBox](https://developer.hashicorp.com/vagrant/docs/providers/virtualbox/common-issues), 
ed in particolare la possibilità che, durante l'avvio con Vagrant di macchine virtuali VirtualBox, 
il sistema host non sia in grado di connettersi in rete con la macchina virtuale.  

E' importante osservare che VirtualBox e VMware Workstation potrebbero risentire di un peggioramento delle prestazioni 
(ovvero, di un rallentamento significativo) 
a causa delle interazioni di VirtualBox e VMware Workstation con Hyper-V e con la Virtualization Based Security di Windows 11 
e con i nuovi processori con core di tipo P/E. 
A tal fine, io ho utilizzato la seguente configurazione di *Windows 11*: 
* disabilitazione di *Hyper-V*: 
  * tra le *Impostazioni* di *Windows 11*, cercare *Attiva o disattiva funzionalità di Windows* 
    (oppure *Attivazione o disattivazione delle funzionalità di Windows*)
    e disabilitare le opzioni *Piattaforma macchina virtuale*, *Piattaforma Windows Hypervisor* e *Hyper-V* 
  * riavviare il computer 
* disabilitazione della *Virtualization Based Security* (*VBS*) (**attenzione: questo migliora le prestazioni di VirtualBox, ma peggiore la sicurezza del sistema**): 
  * per verificare se la VBS è attiva o meno, usare il comando *System Information* di Windows 
    e guardare se la voce *Sicurezza basata sulla virtualizzazione* è abilitata o meno (se non è abilitata allora non serve fare quanto segue)
  * con l'*Editor del Registro di Sistema* cercare la voce 
    *Computer\HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\DeviceGuard* 
	e cambiare il valore di *EnableVirtualizationBasedSecurity* da *1* a *0*  
  * tra le *Impostazioni* di *Windows 11*, cercare *Privacy e Sicurezza*, *Sicurezza di Windows*, *Sicurezza dispositivi*, *Isolamento Core* 
    e disabilitare le opzioni *Integrità della memoria* e *Protezione del firmware* 
  * riavviare il computer 
  * (si noti che è possibile riabilitare la VBS effettuando le modifiche inverse)
* disabilitazione del *power throttling* 
  (per far assegnare a VirtualBox i P-core con buone prestazioni anziché gli E-core più efficienti da un punto di vista energetico): 
  * avviare un *Terminale* in modalità *Admin* (aperto con *Win-X*) ed eseguire i seguenti comandi: 
    * `powercfg /powerthrottling disable /path "C:\Program Files\Oracle\VirtualBox\VBoxHeadless.exe"`
    * `powercfg /powerthrottling disable /path "C:\Program Files\Oracle\VirtualBox\VirtualBoxVM.exe"`
    * per verifica, `powercfg /powerthrottling list` dovrebbe indicare *Never On* sia per *VBoxHeadless.exe* che per *VirtualBoxVM.exe*. 

### Software per lo sviluppo del software 

Ecco il software opzionale per lo sviluppo del software (non è strettamente necessario, poiché può essere eseguito nelle macchine virtuali):
* [OpenJDK](https://openjdk.org/), versione 21  
* [Gradle](http://gradle.org/), versione 8.12.1 


## Organizzazione del repository 

Questo repository è organizzato in diverse sezioni (cartelle): 
* [projects](projects/) contiene il codice delle *applicazioni distribuite*, 
  con una sottosezione (sottocartella) per ciascuno degli argomenti del corso; 
* [environments](environments/) contiene il codice per la gestione degli *ambienti distribuiti*, 
  con una sottosezione (sottocartella) per ciascuno degli ambienti distribuiti 
  su cui poter eseguire le applicazioni distribuite sviluppate; 
* [resources](resources/) contiene ulteriori risorse condivise per la gestione degli *ambienti distribuiti*. 

Queste sezioni non sono indipendenti, ma correlate (in modo non banale). 

Attualmente sono presenti tutti i progetti e tutti gli ambienti, 
**ma alcuni di questi potrebbero ancora riferirsi alla precedente edizione del corso**. 
Durante lo svolgimento del corso tutti i progetti e tutti gli ambienti verranno aggiornati, in modo incrementale. 


## Accesso al repository 

Per effettuare il download del repository, usare il seguente comando Git 
dalla cartella locale in cui si vuole scaricare il repository: 

    git clone https://github.com/aswroma3/asw 

Oppure (se il sistema host è Windows): 

    git clone --config core.autocrlf=input https://github.com/aswroma3/asw 

Per aggiornare il contenuto della propria copia locale del repository, 
usare il seguente comando Git dalla cartella locale in cui è stato scaricato il repository: 

    git pull 
