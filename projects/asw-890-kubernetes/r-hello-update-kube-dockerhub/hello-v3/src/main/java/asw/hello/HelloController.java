package asw.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

@RestController
public class HelloController {

	private static final String VERSION = "3.0 (***)"; 
	
	@Value("${asw.hello.soglia.delay:10}") 
	/* soglia per i primi ritardi */ 
	private int sogliaDelay;

	@Value("${asw.hello.delay:250}") 
	/* ritardo da introdurre */ 
	private int delay;

	@Value("${asw.hello.soglia.failure:25}") 
	/* soglia per i fallimenti */ 
	private int sogliaFailure;

	@Value("${asw.hello.failurerate:33}") 
	/* tasso percentuale di fallimento, tra 0 e 100 */ 
	private int failureRate;

	private Random random = new Random(); 

	private int counter = 0; 

    /* Restituisce un saluto da questo host. */
	@RequestMapping("/")
	public String hello() {
		if (counter<sogliaDelay) {
			counter++; 
			return "Hello, this is version " + VERSION + " from pod " + getHostname() + "!"; 
		} else if (counter<sogliaFailure) {
			counter++; 
			sleep(delay); 
			return "Hello, this is version " + VERSION + " from (slow) pod " + getHostname() + "!"; 
		} else {
			counter++; 
			sleep(delay*2); 
			int randomIndex = random.nextInt(100);
			boolean failure = (randomIndex<failureRate); 
			if (failure) {
				throw new ResponseStatusException(
								HttpStatus.INTERNAL_SERVER_ERROR, 
								"Internal server error in version " + VERSION + " from (chaotic) pod " + getHostname() + "!");
			} else {
				return "Hello, this is version " + VERSION + " from (chaotic) pod " + getHostname() + "!"; 
			}
		}
	}
	
	/* Calcola l'hostname di questo nodo */
	private String getHostname() {
		String result = null; 
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
			String ipAddress = ip.getHostAddress(); 
			result = hostname + " (" + ipAddress + ")"; 
        } catch (Exception e) {
            // e.printStackTrace();
        }
		return result; 
	}

	/* Introduce un ritardo di delay millisecondi. */
	public static void sleep(int delay) {
        try {
        	// int delay = (int)(Math.random()*maxDelay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}
	
}
