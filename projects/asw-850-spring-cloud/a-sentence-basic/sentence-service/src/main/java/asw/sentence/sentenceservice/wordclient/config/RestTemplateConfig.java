package asw.sentence.sentenceservice.wordclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.web.client.RestTemplate;

/* Configurazione per un RestTemplate. */
@Configuration
public class RestTemplateConfig {

   @Bean
   public RestTemplate restTemplate() {
      return new RestTemplate();
   }

}

