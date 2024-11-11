package asw.goodmusic.connessioni.api.rest;

import lombok.*; 

/* Connessione. */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class ConnessioneResponse {

	private Long id; 
	private String utente;
	private String seguito; 
	private String ruolo; 
	
}

