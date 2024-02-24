package asw.socket.server.connector;

import asw.socket.service.Service;

import java.net.*;
import java.io.*;

import java.util.logging.Logger;

/* Remote proxy lato server per il servizio Service. */
public class ServiceServerTCPProxy {

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.server.connector");

    private Service service;             // il vero servizio
    private int port;                           // porta per il servizio

	public ServiceServerTCPProxy(Service service, int port) {
        this.service = service;
        this.port = port;
    }

    public void run() {
        try {
            /* crea il server socket su cui ascoltare/ricevere richieste */
        	ServerSocket listenSocket = new ServerSocket(port);
            /* per il server, disabilita il timeout */
        	listenSocket.setSoTimeout(0);
            while (true) {
                /* aspetta/accetta una richiesta - crea il relativa socket */
				Socket clientSocket = listenSocket.accept();    // bloccante
				/* la richiesta sara' gestita in un nuovo popup thread, separato: 
 				 * crea e avvia il nuovo thread */
				ServerThread thread = new ServerThread(clientSocket, service);
				thread.start(); 
				/* poi torna immediatamente ad aspettare la richiesta successiva */ 
            }
		} catch (IOException e) {
			logger.info("Server Proxy: IO Exception: " + e.getMessage());
		}
    }

}

