package asw.spring.show;

/* Un musicista (suona uno strumento). */
public class Musician implements Artist {

	/* il nome del musicista */
	private String name;

	/* lo strumento suonato dal musicista */
	private Instrument instrument;

	/* Crea un nuovo musicista. */
	public Musician(String name, Instrument instrument) {
		this.name = name; 
		this.instrument = instrument;
	}

	/* Esibizione del musicista. */
	public String perform() {
		return "I'm " + name + ": " + instrument.play(); 
	}

}
