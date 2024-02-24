package asw.sentence.sentenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableRetry
public class SentenceAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentenceAsyncApplication.class, args);
	}
}
