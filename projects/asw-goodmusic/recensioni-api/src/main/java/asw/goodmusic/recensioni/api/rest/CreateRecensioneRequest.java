package asw.goodmusic.recensioni.api.rest;

import lombok.*;

/* Per creare una recensione. */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class CreateRecensioneRequest {

	private String recensore; 
	private String album; 
	private String artista; 
	private String genere; 
	private String testo; 
	private String sunto; 

}

