package asw.goodmusic.recensioniseguite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RecensioniSeguiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecensioniSeguiteApplication.class, args);
	}
}
