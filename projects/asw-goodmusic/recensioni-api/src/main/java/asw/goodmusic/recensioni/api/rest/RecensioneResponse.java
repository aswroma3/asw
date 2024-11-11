package asw.goodmusic.recensioni.api.rest;

import lombok.*; 

/* Una recensione, in formato completo. */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class RecensioneResponse {

	private Long id; 
	private String recensore; 
	private String album; 
	private String artista; 
	private String genere; 
	private String testo; 
	private String sunto; 
	
}

