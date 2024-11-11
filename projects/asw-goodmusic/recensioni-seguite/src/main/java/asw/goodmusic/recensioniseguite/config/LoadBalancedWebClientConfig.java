package asw.goodmusic.recensioniseguite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.web.util.DefaultUriBuilderFactory; 

/* Configurazione per un Web Client load balanced. */
@Configuration
public class LoadBalancedWebClientConfig {

	@Bean("loadBalancedWebClientBuilder")
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
    
    @Bean("loadBalancedWebClient")
    public WebClient loadBalancedWebClient(WebClient.Builder loadBalancedWebClientBuilder) {
		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
		factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        return loadBalancedWebClientBuilder
					.uriBuilderFactory(factory)
					.build();
    }

}

