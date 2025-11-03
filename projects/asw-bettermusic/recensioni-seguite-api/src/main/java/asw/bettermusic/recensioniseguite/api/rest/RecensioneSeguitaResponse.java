package asw.bettermusic.recensioniseguite.api.rest;

import lombok.*; 

/* Una recensione seguita (senza il testo completo della recensione). */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class RecensioneSeguitaResponse {

	private Long id; 
	private String recensore; 
	private Long idAlbum; 
	private String sunto; 
	
}

