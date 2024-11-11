package asw.goodmusic.apigateway.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*; 

@RestController
public class DefaultController {

	@GetMapping("/")
	public Collection<String> index() {
		return Arrays.asList(
			"http://localhost:8080/recensioni/swagger-ui/index.html", 
			"http://localhost:8080/connessioni/swagger-ui/index.html", 
			"http://localhost:8080/recensioni-seguite/swagger-ui/index.html", 
			"http://localhost:8080/actuator" 
		); 
	}
	
}
