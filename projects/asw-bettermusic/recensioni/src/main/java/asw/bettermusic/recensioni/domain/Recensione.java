package asw.bettermusic.recensioni.domain;

import jakarta.persistence.*; 

import lombok.*; 

/* Recensione di un album scritta da un recensore. */ 
@Entity 
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Recensione implements Comparable<Recensione> {

	/* id della recensione */
	@Id @GeneratedValue
	@EqualsAndHashCode.Include
	private Long id; 
	
	/* chi ha scritto la recensione */ 
	private String recensore; 
	/* id dell'album oggetto della recensione */ 
	private Long idAlbum; 
	/* testo della recensione */ 
	private String testo; 
	/* sunto del testo della recensione */ 
	private String sunto; 

	public Recensione(String recensore, Long idAlbum, String testo, String sunto) {
		this(); 
		this.recensore = recensore; 
		this.idAlbum = idAlbum; 
		this.testo = testo; 
		this.sunto = sunto; 
	}

	@Override
	public int compareTo(Recensione other) {
		return this.id.compareTo(other.id); 
	}
	
}
