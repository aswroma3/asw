package asw.intro.server;

import asw.intro.service.Service;

import asw.intro.server.connector.ServiceServerProxy;

import java.util.logging.Logger;

/* Server per il servizio:
 * Questa applicazione crea il servizio e avvia il server. */
public class Server {

	/* logger */
	private static Logger logger = Logger.getLogger("asw.intro.server");

	/* porta del server */
	private static final int SERVER_PORT = 6789;

	/* Avvio del server. */
	public static void main(String[] args) {
		logger.info("Server...");
        Service service = new ServiceImpl();
        int port = SERVER_PORT;
		logger.info("Server: starting... (type CTRL+C to stop)");
        ServiceServerProxy server = new ServiceServerProxy(service, port);
        server.run();
		logger.info("Server: stopped");
	}

}


