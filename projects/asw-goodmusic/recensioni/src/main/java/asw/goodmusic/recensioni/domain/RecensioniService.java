package asw.goodmusic.recensioni.domain;

import org.springframework.stereotype.Service;

import java.util.*; 

@Service
public interface RecensioniService {

	/* Crea una nuova recensione, a partire dai suoi dati. */ 
 	public Recensione createRecensione(String recensore, String album, String artista, String genere, String testo, String sunto); 

	/* Trova una recensione, dato l'id. */ 
 	public Recensione getRecensione(Long id); 

	/* Trova tutte le recensioni. */ 
	public Collection<Recensione> getRecensioni(); 

	/* Trova tutte le recensioni di un album. */ 
	public Collection<Recensione> getRecensioniByAlbum(String album); 

	/* Trova tutte le recensioni scritte da un recensore. */ 
	public Collection<Recensione> getRecensioniByRecensore(String recensore); 

	/* Trova tutte le recensioni scritte da un insieme di recensori. */ 
	public Collection<Recensione> getRecensioniByRecensori(Collection<String> recensori); 

	/* Trova tutte le recensioni degli album di un artista. */ 
	public Collection<Recensione> getRecensioniByArtista(String artista); 

	/* Trova tutte le recensioni degli album di un insieme di artisti. */ 
	public Collection<Recensione> getRecensioniByArtisti(Collection<String> artisti); 

	/* Trova tutte le recensioni degli album di un certo genere. */ 
	public Collection<Recensione> getRecensioniByGenere(String genere); 

	/* Trova tutte le recensioni degli album di un insieme di generi. */ 
	public Collection<Recensione> getRecensioniByGeneri(Collection<String> generi); 

}
