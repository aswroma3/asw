package asw.socket.server;

import asw.socket.service.Service;
import asw.socket.service.impl.ServiceImpl;
import asw.socket.server.connector.ServiceServerTCPProxy;

import java.util.logging.Logger;

/* server per il servizio */
public class Server {

	/* logger */
	private static Logger logger = Logger.getLogger("asw.socket.server");

	private static int SERVER_PORT = 7896;

	public static void main(String[] args) {
        Service service = new ServiceImpl();
		logger.info("Server: starting... (type CTRL+C to stop)");
        ServiceServerTCPProxy server = new ServiceServerTCPProxy(service, SERVER_PORT);
        server.run();
        logger.info("Server: stopped");
    }
}


