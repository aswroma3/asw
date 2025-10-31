package asw.hello.domain;

import org.springframework.stereotype.Service;

import java.util.logging.*; 

@Service
public class HelloService {

	private Logger logger = Logger.getLogger(this.getClass().toString());

 	public String sayHello(String name) {
		String greeting = "Hello, " + name + "!"; 
		logger.info("sayHello(" + name + "): " + greeting);
		return greeting;
	}

}

