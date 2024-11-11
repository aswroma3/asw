package asw.goodmusic.recensioni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class RecensioniApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecensioniApplication.class, args);
	}
}
