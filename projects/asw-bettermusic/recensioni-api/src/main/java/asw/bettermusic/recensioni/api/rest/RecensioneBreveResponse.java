package asw.bettermusic.recensioni.api.rest;

import lombok.*; 

/* Una recensione, in formato breve (senza il testo completo della recensione). */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class RecensioneBreveResponse {

	private Long id; 
	private String recensore; 
	private Long idAlbum; 
	private String sunto; 
	
}

