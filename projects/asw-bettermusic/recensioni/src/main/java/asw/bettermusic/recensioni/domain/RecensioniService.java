package asw.bettermusic.recensioni.domain;

import org.springframework.stereotype.Service;

import java.util.*; 

@Service
public interface RecensioniService {

	/* Crea una nuova recensione, a partire dai suoi dati. */ 
 	Recensione createRecensione(String recensore, String titoloAlbum, String artistaAlbum, String testo, String sunto); 

	/* Trova una recensione, dato l'id. */ 
 	Recensione getRecensione(Long id); 

	/* Trova tutte le recensioni. */ 
	Collection<Recensione> getRecensioni(); 

	/* Trova tutte le recensioni di un album, dato l'id. */ 
	Collection<Recensione> getRecensioniByIdAlbum(Long idAlbum); 

	/* Trova tutte le recensioni scritte da un recensore. */ 
	Collection<Recensione> getRecensioniByRecensore(String recensore); 

}
