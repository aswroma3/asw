package asw.sentence.wordservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class WordApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordApplication.class, args);
	}
}
