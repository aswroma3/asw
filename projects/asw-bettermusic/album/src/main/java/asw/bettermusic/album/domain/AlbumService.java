package asw.bettermusic.album.domain;

import org.springframework.stereotype.Service;

import java.util.*; 

@Service
public interface AlbumService {

	/* Crea un nuovo album, a partire dai suoi dati. */ 
 	Album createAlbum(String titolo, String artista, Set<String> generi); 

	/* Trova un album, dato l'id. */ 
 	Album getAlbum(Long id); 

	/* Trova un album, dati titolo e artista. */ 
 	Album getAlbum(String titolo, String artista); 

	/* Trova tutti gli album. */ 
	Collection<Album> getAlbum(); 

	/* Trova tutti gli album di un artista. */ 
	Collection<Album> getAlbumByArtista(String artista); 

	/* Trova tutti gli album di un genere. */ 
	Collection<Album> getAlbumByGenere(String genere); 

}
