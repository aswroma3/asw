package asw.bettermusic.recensioniseguite.domain;

import java.util.*; 

public interface ConnessioniClientPort {

	Collection<Connessione> getConnessioniByUtente(String utente); 
	
	Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo); 

}
