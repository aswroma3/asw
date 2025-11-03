package asw.bettermusic.recensioniseguite.domain;

import lombok.*; 

/* Recensione di un album scritta da un recensore. 
 * Una recensione nel dominio delle recensioni-seguite 
 * corrisponde in effetti ad una recensione "breve" nel dominio delle recensioni. */ 
@Data @NoArgsConstructor @AllArgsConstructor
public class Recensione implements Comparable<Recensione> {

	/* id della recensione */
	@EqualsAndHashCode.Include
	private Long id; 
	/* chi ha scritto la recensione */ 
	private String recensore; 
	/* id dell'album oggetto della recensione */ 
	private Long idAlbum; 
	/* sunto del testo della recensione */ 
	private String sunto; 

	@Override
	public int compareTo(Recensione other) {
		return this.id.compareTo(other.id); 
	}
	
}
