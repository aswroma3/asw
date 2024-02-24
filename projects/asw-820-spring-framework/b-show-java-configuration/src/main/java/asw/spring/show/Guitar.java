package asw.spring.show;

/* Una chitarra. */
public class Guitar implements Instrument {

	/* il suono della chitarra */
	private String sound;

	/* Crea una nuova chitarra. */
	public Guitar() {
	}

	/* Assegna il suono della chiatarra. */ 
	public void setSound(String sound) {
		this.sound = sound; 
	}
	
	/* Suona la chitarra. */
	public String play() {
		return sound; 
	}

}

