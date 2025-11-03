package asw.bettermusic.recensioniseguite.domain;

import java.util.*; 

public interface RecensioniClientPort {

	Collection<Recensione> getRecensioniByRecensore(String recensore); 

	Collection<Recensione> getRecensioniByIdAlbum(Long idAlbum); 

}
