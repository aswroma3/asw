package asw.goodmusic.connessioni.domain;

import jakarta.persistence.*; 

import lombok.*; 

/* Connessione tra un utente e un seguito (con un ruolo). 
 * Il seguito può essere un artista oppure uno che scrive recensioni oppure un genere musicale. */  
@Entity 
@Data @NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueUtenteSeguitoRuolo", columnNames = { "utente", "seguito", "ruolo" }) })
public class Connessione {

	@Id @GeneratedValue
	/* id della connessione */ 
	private Long id; 
	/* utente che segue */ 
	private String utente;
	/* chi o cosa è seguito (un artista oppure uno che scrive recensioni oppure un genere musicale) */ 
	private String seguito; 
	/* ruolo del seguito: può essere ARTISTA oppure RECENSORE oppure GENERE */ 
	private String ruolo; 
	
	public Connessione(String utente, String seguito, String ruolo) {
		this(); 
		this.utente = utente; 
		this.seguito = seguito; 
		this.ruolo = ruolo; 
	}
	
}
