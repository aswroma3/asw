package asw.bettermusic.album.domain;

import jakarta.persistence.*; 

import lombok.*; 

import java.util.*; 

/* Un album di un artista, con i suoi generi. */ 
@Entity 
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueTitoloArtista", columnNames = { "titolo", "artista" }) })
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Album implements Comparable<Album> {

	/* id dell'album */
	@Id @GeneratedValue
	@EqualsAndHashCode.Include
	private Long id; 
	
	/* il titolo dell'album */ 
	private String titolo; 
	/* artista autore dell'album */ 
	private String artista; 
	/* generi dell'album */ 
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	private Set<String> generi; 

	public Album(String titolo, String artista, Set<String> generi) {
		this(); 
		this.titolo = titolo; 
		this.artista = artista; 
		this.generi = generi; 
	}

	@Override
	public int compareTo(Album other) {
		return this.id.compareTo(other.id); 
	}
	
}
