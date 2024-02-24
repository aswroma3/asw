package asw.spring.show;

import org.springframework.stereotype.Component; 
import org.springframework.beans.factory.annotation.Value;

/* Una chitarra. */
@Component(value="stratocaster")
public class Guitar implements Instrument {

	/* il suono della chitarra */
	@Value("${show.stratocaster.sound}")
	private String sound;

	/* Suona la chitarra. */
	public String play() {
		return sound; 
	}

}

