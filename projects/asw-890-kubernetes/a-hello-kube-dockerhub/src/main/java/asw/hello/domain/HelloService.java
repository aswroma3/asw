package asw.hello.domain;

import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public class HelloService {

    /* Calcola il saluto Hello from this host! */
	public String hello() {
		return "Hello from " + getHostname() + "!"; 
	}
	
	/* Calcola l'hostname di questo nodo */
	private String getHostname() {
//		return System.getenv("HOSTNAME");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
			String ipAddress = ip.getHostAddress(); 
			// hostname += " (" + ipAddress + ")"; 
			return hostname; 
        } catch (Exception e) {
            // e.printStackTrace();
        }
		return null; 
	}

}

