package asw.spring.show.config;

import asw.spring.show.*; 

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

/* Configurazione Spring per l'applicazione Show. */
@Configuration
public class ShowConfig {

	@Bean
	public Artist hendrix() {
		return new Musician("Jimi", stratocaster()); 
	}
	
	@Bean 
	public Instrument stratocaster() {
		Guitar stratocaster = new Guitar(); 
		stratocaster.setSound("Ua ua uaa");
		return stratocaster; 
	}
	
	@Bean
	public Artist may() {
		return new Musician("Brian", redspecial()); 
	}
	
	@Bean 
	public Instrument redspecial() {
		Guitar redspecial = new Guitar(); 
		redspecial.setSound("La la laa");
		return redspecial; 
	}

}
