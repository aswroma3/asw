package asw.sentence.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory; 
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import java.time.Duration;

/* Configurazione per Resilience4J. */
@Configuration
public class Resilience4JConfig {

	@Value("${asw.resilience4j.timelimiter.timeoutduration:1000}") 
	private int timeoutDuration;

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		/* ATTENZIONE: 
		 * QUESTA CONFIGURAZIONE E' PENSATA SOLO PER EFFETTUARE DEGLI ESPERIMENTI
		 * MA NON VA USATA IN PRODUZIONE */ 
		
		/* setta un timeout per il circuit breaker (il default di Resilience4J è 1000 ms) */
		TimeLimiterConfig myTimeLimiterConfig = 
			TimeLimiterConfig.custom()
					.timeoutDuration(Duration.ofMillis(timeoutDuration))
					.build(); 
		return factory -> factory.configureDefault(
				id -> new Resilience4JConfigBuilder(id)
					.timeLimiterConfig(myTimeLimiterConfig) 
					.build());
	}

}

