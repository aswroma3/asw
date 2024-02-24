package asw.sentence.sentenceservice.wordclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.web.reactive.function.client.WebClient;

/* Configurazione per un Web Client. */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

}

