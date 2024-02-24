# Sentence per Kubernetes (Helm) 

## Guida alla configurazione 

La configurazione di Helm è nella cartella Charts 

templates/Notes.txt riporta i commenti per l'avvio 

templates/sentence-application.yaml è il vero template, parametrico 

Chart.yaml definisce la versione (da modificare di anno in anno) 

values.yaml definisce i parametri di configurazione di base da utilizzare (compresa la versione delle immagini da utilizzare, da modificare di anno in anno) 

replicated-values.yaml e with-delays-values.yaml definisci delle variazioni rispetto alla configurazione di base 


