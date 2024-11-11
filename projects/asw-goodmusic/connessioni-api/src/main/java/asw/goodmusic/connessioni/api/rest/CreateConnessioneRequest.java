package asw.goodmusic.connessioni.api.rest;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateConnessioneRequest {

	private String utente; 
	private String seguito; 
	private String ruolo; 

}

