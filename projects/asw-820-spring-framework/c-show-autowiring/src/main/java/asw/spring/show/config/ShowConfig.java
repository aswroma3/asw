package asw.spring.show.config;

import asw.spring.show.*; 

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/* Configurazione Spring per l'applicazione Show. */
@Configuration
@ComponentScan("asw.spring.show")
@PropertySource("classpath:config.properties")
public class ShowConfig {

}
