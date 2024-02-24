package asw.hello.rest;

import asw.hello.domain.HelloService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 

import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

@RestController
public class HelloRestController {

	private Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired 
    private HelloService helloService;

    /* Restituisce un saluto a "name"
     * acceduta come GET /hello/{name} */
	@GetMapping("/hello/{name}")
	public String sayHello(@PathVariable String name) {
		logger.info("REST server: sayHello(" + name + ")");
		String greeting = helloService.sayHello(name); 
		logger.info("REST server: sayHello(" + name + "): " + greeting);
		return greeting; 
	}

}
