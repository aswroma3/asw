package asw.bettermusic.recensioni.api.rest;

import lombok.*; 

/* Una recensione, in formato completo. */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class RecensioneResponse {

	private Long id; 
	private String recensore; 
	private Long idAlbum; 
	private String testo; 
	private String sunto; 
	
}

