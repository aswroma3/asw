package asw.goodmusic.recensioni.api.rest;

import lombok.*; 

/* Una recensione, in formato breve (senza il testo completo della recensione). */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class RecensioneBreveResponse {

	private Long id; 
	private String recensore; 
	private String album; 
	private String artista; 
	private String genere; 
	private String sunto; 
	
}

