package asw.intro.client.context;

import asw.intro.client.Client;
import asw.intro.service.Service;
import asw.intro.client.connector.ServiceClientProxy;

import java.net.InetAddress;

import java.util.logging.Logger;

/* Application context per il servizio Service e il client Client. */
public class ApplicationContext {

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.client.context");

	/* posizione di default del server */
	private static final String DEFAULT_SERVER_ADDRESS = "localhost";
	// private static final String DEFAULT_SERVER_ADDRESS = "10.11.1.121";

	/* porta del server */
	private static int SERVER_PORT = 6789;

    /* il singleton per l'application context */
	private static ApplicationContext instance = null;

	/* il riferimento al servizio */
	private Service service = null;

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
    public synchronized Service getService() {
		logger.info("ApplicationContext: getService()");
    	Service proxy = null;
    	try {
    		InetAddress address = InetAddress.getByName(DEFAULT_SERVER_ADDRESS);
    		int port = SERVER_PORT;
    		proxy = new ServiceClientProxy(address, port);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return proxy;
	}

    public synchronized Service getService(String serverHost) {
		logger.info("ApplicationContext: getService(" + serverHost + ")");
    	Service proxy = null;
    	try {
    		InetAddress address = InetAddress.getByName(serverHost);
    		int port = SERVER_PORT;
    		proxy = new ServiceClientProxy(address, port);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return proxy;
	}

    /* Factory method per il client Client.
     * Ogni volta viene restituito un nuovo client. */
    public Client getClient() {
		logger.info("ApplicationContext: getClient()");
		Client client = new Client();
    	client.setService( this.getService() );
        return client;
    }

    public Client getClient(String serverHost) {
		logger.info("ApplicationContext: getClient(" + serverHost + ")");
		Client client = new Client();
    	client.setService( this.getService(serverHost) );
        return client;
    }
}

