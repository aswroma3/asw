package asw.bettermusic.album.api.rest;

import lombok.*;

import java.util.*; 

/* Per creare un album. */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class CreateAlbumRequest {

	private String titolo; 
	private String artista; 
	private Set<String> generi; 

}

