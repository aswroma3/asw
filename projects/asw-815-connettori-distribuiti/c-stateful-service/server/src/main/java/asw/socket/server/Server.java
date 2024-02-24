package asw.socket.server;

import asw.socket.server.connector.*;

import java.util.logging.Logger;

/* server per il servizio */
public class Server {

	/* logger */
	private static Logger logger = Logger.getLogger("asw.socket.server");

	private static int SERVER_PORT = 7869;

    public static void main(String[] args) {
		logger.info("Server: starting... (type CTRL+C to stop)");
        CounterServiceServerTCPProxy server = new CounterServiceServerTCPProxy(SERVER_PORT);
        server.run();
        logger.info("Server: stopped");
    }
}


