package asw.efood.samplerestaurantclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RestaurantRestAsyncClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantRestAsyncClientApplication.class, args).close();
	}

}

