package asw.bettermusic.recensioni.api.rest;

import lombok.*;

/* Per creare una recensione. */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class CreateRecensioneRequest {

	private String recensore; 
	private String titoloAlbum; 
	private String artistaAlbum; 
	private String testo; 
	private String sunto; 

}

