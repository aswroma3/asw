package asw.bettermusic.album.api.rest;

import lombok.*; 

import java.util.*; 

/* Un album. */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class AlbumResponse {

	private Long id; 
	private String titolo; 
	private String artista; 
	private Set<String> generi; 
	
}

