package asw.socket.server;

import asw.socket.service.Service;
import asw.socket.service.impl.ServiceImpl;
import asw.socket.server.connector.ServiceServerUDPProxy;

import java.util.logging.Logger;

/* server per il servizio */
public class Server {

	private static int SERVER_PORT = 6789;

	/* logger */
	private static Logger logger = Logger.getLogger("asw.socket.server");

	public static void main(String[] args) {
        Service service = new ServiceImpl();
		logger.info("Server: starting... (type CTRL+C to stop)");
        ServiceServerUDPProxy server = new ServiceServerUDPProxy(service, SERVER_PORT);
        server.run();
        logger.info("Server: stopped");
    }
}


