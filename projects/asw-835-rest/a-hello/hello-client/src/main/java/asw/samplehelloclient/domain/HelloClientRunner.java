package asw.samplehelloclient.domain;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.*;

@Component
public class HelloClientRunner implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private HelloClientPort helloClientAdapter;

	public void run(String... args) throws InterruptedException {
		logger.info( helloClientAdapter.sayHello("Luca") );
		logger.info( helloClientAdapter.sayHello("World") );
	}
}
