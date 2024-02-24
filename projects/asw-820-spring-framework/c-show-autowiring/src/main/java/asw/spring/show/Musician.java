package asw.spring.show;

import org.springframework.stereotype.Component; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;

/* Un musicista (suona uno strumento). */
@Component(value="hendrix")
public class Musician implements Artist {

	/* il nome del musicista */
	@Value("${show.hendrix.name}")
	private String name;

	/* lo strumento suonato dal musicista */
	@Autowired 
	@Qualifier("stratocaster")
	private Instrument instrument;

	/* Esibizione del musicista. */
	public String perform() {
		return "I'm " + name + ": " + instrument.play(); 
	}

}
