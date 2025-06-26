# Lucky word (versione per Docker)

## Modifiche rispetto alla versione asw-825-spring-boot/i-lucky-word-profiles

Nel codice: 
* NESSUNA 

Nella configurazione: 
* NESSUNA 

Nei file GRADLE: 
* in setting.gradle, riristinato rootProject.name = 'lucky-word' 
  per garantire che il nome del file jar sia proprio lucky-word.jar 
  anziché a-lucky-word.jar (scelto sulla base del nome della cartella che contiene il progetto) 
  
Inoltre: 
* aggiunto Dockerfile al progetto