package asw.goodmusic.connessioni.domain;

import org.springframework.stereotype.Service;

import java.util.*; 

@Service
public interface ConnessioniService {

	/* Crea una nuova connessione, dati utente, seguito e ruolo. */ 
 	public Connessione createConnessione(String utente, String seguito, String ruolo); 

	/* Trova una connessione, dato l'id. */ 
 	public Connessione getConnessione(Long id); 

	/* Trova una connessione, dati utente, seguito e ruolo. */ 
	public Connessione getConnessione(String utente, String seguito, String ruolo); 

	/* Trova tutte le connessioni. */ 
 	public Collection<Connessione> getConnessioni(); 

	/* Trova tutte le connessioni di un utente. */ 
	public Collection<Connessione> getConnessioniByUtente(String utente); 

	/* Trova tutte le connessioni con un certo ruolo. */ 
	public Collection<Connessione> getConnessioniByRuolo(String ruolo); 

	/* Trova tutte le connessioni di un utente con un certo ruolo. */ 
	public Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo); 

	/* Cancella una connessione, dati utente, seguito e ruolo. */ 
 	public Connessione deleteConnessione(String utente, String seguito, String ruolo); 

}
