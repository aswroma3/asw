package asw.ordermanager.apigateway.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*; 

@RestController
public class DefaultController {

	@GetMapping("/")
	public Collection<String> index() {
		return Arrays.asList(
			"http://localhost:8080/orderservice/swagger-ui/index.html", 
			"http://localhost:8080/productservice/swagger-ui/index.html", 
			"http://localhost:8080/ordervalidationservice/swagger-ui/index.html",
			"http://localhost:8080/actuator" 
		); 
	}
	
}
