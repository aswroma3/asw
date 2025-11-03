package asw.bettermusic.recensioniseguite.domain;

import java.util.*; 

public interface AlbumClientPort {

	Album getAlbum(Long albumId); 

	Album getAlbum(String titoloAlbum, String artistaAlbum); 
	
	Collection<Album> getAlbumByArtista(String artista);

	Collection<Album> getAlbumByGenere(String genere);

}
