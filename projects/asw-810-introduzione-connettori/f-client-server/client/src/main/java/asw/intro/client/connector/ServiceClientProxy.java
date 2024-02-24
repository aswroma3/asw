package asw.intro.client.connector;

import asw.intro.service.Service;

import java.net.*;    // per le socket

import java.util.logging.Logger;

/* remote proxy lato client per il servizio */
public class ServiceClientProxy implements Service {
    private InetAddress address;    // indirizzo del server
    private int port;                           // porta per il servizio

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.client.connector");

	public ServiceClientProxy(InetAddress address, int port) {
		logger.info("ServiceClientProxy: Created a new Remote Proxy for Service " + address.toString() + ":" + port);
        this.address = address;
        this.port = port;
    }

    /* questo e' proprio il metodo invocato dal client
     * (anche se il client pensa di parlare direttamente con il servizio) */
    public String alpha(String arg) {
    	String result = null;
        try {
            /* crea un datagramma di richiesta che codifica 
			 * l'invocazione del servizio e i relativi parametri */
            /* la richiesta ha la forma "operazione#parametro" */
        	String request = "alpha#" + arg;
            byte[] requestMessage = request.getBytes();
            DatagramPacket requestPacket =
                 new DatagramPacket(requestMessage,
                                    requestMessage.length,
                                    this.address, this.port);
            /* invia il datagramma di richiesta */
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(5000);  // il timeout e' 5 secondi
     		logger.info("Client Proxy: calling alpha(" + arg + ")");
     		logger.info("Client Proxy: sending message: " + request);
            socket.send(requestPacket);    // non bloccante
            /* riceve il datagramma di risposta */
            byte[] buffer = new byte[1000];
            DatagramPacket replyPacket =
                new DatagramPacket(buffer, buffer.length);
            socket.receive(replyPacket);    // bloccante
            /* estrae il risultato dal datagramma di risposta */
            /* la risposta ha la forma "risposta" */
            result = new String( replyPacket.getData(),
                                 replyPacket.getOffset(),
                                 replyPacket.getLength() );
      		logger.info("Client Proxy: received message: " + result);
     		logger.info("Client Proxy: alpha(" + arg + ") ==> " + result);
     		socket.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return result;
    }
}

