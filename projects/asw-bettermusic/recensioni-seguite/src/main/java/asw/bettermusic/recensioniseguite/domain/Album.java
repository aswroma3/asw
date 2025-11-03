package asw.bettermusic.recensioniseguite.domain;

import lombok.*; 

import java.util.*; 

/* Un album. */  
@Data @NoArgsConstructor @AllArgsConstructor
public class Album {

	/* id dell'album */ 
	private Long id; 
	/* titolo dell'album */ 
	private String titolo; 
	/* artista dell'album */ 
	private String artista; 
	/* generi dell'album */ 
	private Set<String> generi; 
	
}
