package asw.bettermusic.recensioni.domain;

import java.util.*; 

public interface AlbumClientPort {

	Album getAlbum(String titoloAlbum, String artistaAlbum); 
	
}
