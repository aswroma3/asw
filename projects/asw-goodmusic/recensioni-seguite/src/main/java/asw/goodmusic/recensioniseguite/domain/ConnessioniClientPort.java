package asw.goodmusic.recensioniseguite.domain;

import java.util.*; 

public interface ConnessioniClientPort {

	public Collection<Connessione> getConnessioniByUtente(String utente); 
	
	public Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo); 

}
