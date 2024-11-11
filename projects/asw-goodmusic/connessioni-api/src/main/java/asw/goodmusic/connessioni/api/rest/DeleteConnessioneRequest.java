package asw.goodmusic.connessioni.api.rest;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class DeleteConnessioneRequest {

	private String utente; 
	private String seguito; 
	private String ruolo; 

}

