package asw.bettermusic.recensioniseguite.domain;

import lombok.*; 

/* Connessione tra un utente e un seguito (con un ruolo). */  
@Data @NoArgsConstructor @AllArgsConstructor
public class Connessione {

	/* id della connessione */ 
	private Long id; 
	/* utente che segue */ 
	private String utente; 
	/* chi o cosa è seguito (un artista o uno che scrive recensioni oppure un genere musicale) */ 
	private String seguito; 
	/* ruolo del seguito: può essere ARTISTA oppure RECENSORE oppure GENERE */ 
	private String ruolo; 
	
}
