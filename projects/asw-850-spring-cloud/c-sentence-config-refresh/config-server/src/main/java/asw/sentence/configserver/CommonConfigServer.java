package asw.sentence.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CommonConfigServer {

	public static void main(String[] args) {
		SpringApplication.run(CommonConfigServer.class, args);
	}
}
