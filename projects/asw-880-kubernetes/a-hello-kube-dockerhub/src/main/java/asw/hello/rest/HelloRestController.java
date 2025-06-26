package asw.hello.rest;

import asw.hello.domain.HelloService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

@RestController
public class HelloRestController {

	private Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired 
    private HelloService helloService;

    /* Restituisce un saluto da questo host. */
	@GetMapping("/")
	public String hello() {
		String greeting = helloService.hello(); 
		logger.info("Hello.hello(): " + greeting);
		return greeting; 
	}

}
