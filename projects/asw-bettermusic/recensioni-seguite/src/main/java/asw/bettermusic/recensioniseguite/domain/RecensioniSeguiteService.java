package asw.bettermusic.recensioniseguite.domain;

import org.springframework.stereotype.Service;

import java.util.*; 

@Service 
public interface RecensioniSeguiteService {

	/* Trova le recensioni seguite da un utente, 
	 * ovvero le recensioni degli album degli artisti, dei recensori e dei generi musicali seguiti da quell'utente. */ 
	Collection<Recensione> getRecensioniSeguite(String utente); 

}
