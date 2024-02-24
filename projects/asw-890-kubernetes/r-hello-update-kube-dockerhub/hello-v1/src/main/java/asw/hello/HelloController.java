package asw.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;

@RestController
public class HelloController {

	private static final String VERSION = "1.0 (*)"; 

    /* Restituisce un saluto da questo host. */
	@RequestMapping("/")
	public String hello() {
		return "Hello, this is version " + VERSION + " from pod " + getHostname() + "!"; 
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

}
