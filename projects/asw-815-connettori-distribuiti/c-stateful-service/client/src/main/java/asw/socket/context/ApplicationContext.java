package asw.socket.context;

import asw.socket.client.Client;
import asw.socket.service.RemoteCounterService;
import asw.socket.client.connector.ServiceClientTCPProxy;

import java.net.InetAddress;

import java.util.logging.Logger;

/* Application context per il servizio Service e il client Client. */
public class ApplicationContext {

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.context");

	/* posizione del server */
	private static String SERVER_ADDRESS = "localhost";
	// private static String SERVER_ADDRESS = "10.11.1.121";

	/* porta del server */
	private static int SERVER_PORT = 7869;

    /* il singleton per l'application context */
	private static ApplicationContext instance = null;

	/* Costruttore dell'application context (privato per singleton). */
	private ApplicationContext() {
		logger.info("Creazione dell'Application Context");
	}

    public static synchronized ApplicationContext getInstance() {
        if (instance==null) {
        	instance = new ApplicationContext();
        }
        return instance;
    }

    /* Factory method per il servizio Service.
     * Se possibile viene restituito uno stesso servizio. */
    public synchronized RemoteCounterService getService() {
		return getService(SERVER_ADDRESS);
	}

    public synchronized RemoteCounterService getService(String serverHost) {
		logger.info("ApplicationContext: getService(" + serverHost + ")");
    	RemoteCounterService proxy = null;
    	try {
    		InetAddress address = InetAddress.getByName(serverHost);
    		int port = SERVER_PORT;
    		proxy = new ServiceClientTCPProxy(address, port);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return proxy;
	}

    /* Factory method per il client Client.
     * Ogni volta viene restituito un nuovo client. */
    public Client getClient() {
		return getClient(SERVER_ADDRESS);
    }

    public Client getClient(String serverHost) {
		logger.info("ApplicationContext: getClient(" + serverHost + ")");
		Client client = new Client();
    	client.setService( this.getService(serverHost) );
        return client;
    }
}

