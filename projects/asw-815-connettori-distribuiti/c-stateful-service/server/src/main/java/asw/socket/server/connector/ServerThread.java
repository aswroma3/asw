package asw.socket.server.connector;

import asw.socket.service.CounterService;
import asw.socket.service.RemoteException;

import java.net.*;
import java.io.*;

import java.util.logging.Logger;

public class ServerThread extends Thread {

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.server.connector");

	private CounterService service;

	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;

	boolean done = false;   // e' finita la sessione?

	private static int MAX_SERVER_THREAD_ID = 0;
	private int serverThreadId;

	public ServerThread(Socket clientSocket, CounterService service) {
		try {
			this.clientSocket = clientSocket;
			this.service = service;
			this.serverThreadId = MAX_SERVER_THREAD_ID++;
			/* potrebbero anche andare all'inizio del metodo run */ 
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			logger.info("Server Proxy: IO Exception: " + e.getMessage());
		}
	}

	/* run eseguito in un nuovo thread */
	public void run() {
		logger.info("Server Proxy: opening connection [" + serverThreadId + "]");
		try {
			while (!done) {
				/* riceve una richiesta */
				String request = in.readUTF();  // bloccante
	    		logger.info("Server Proxy: connection [" + serverThreadId + "]: received request: " + request);

	            /* estrae operazione */
	            /* la richiesta ha la forma "operazione" */
	            String op = request;

	            /* chiedi l'erogazione del servizio ed ottieni la risposta */
	            String result = null;
	            try {
		            result = this.executeOperation(op);
	    		} catch (RemoteException e) {
	                /* il servente non solleva MAI RemoteException, 
					 * ma si può arrivare qui da executeOperation() 
					 * se la richiesta è malformata */
	                result = "";
	            }

	            /* prepara la risposta da restituire */
	            /* la risposta ha la forma "risultato" */
	            String reply = result;
	    		logger.info("Server Proxy: connection [" + serverThreadId + "]: sending reply: " + result);
				/* invia la risposta */
	            out.writeUTF(reply);    // non bloccante
			}
		} catch (EOFException e) {
			logger.info("Server Proxy: connection [" + serverThreadId + "]: EOFException: " + e.getMessage());
		} catch (IOException e) {
			logger.info("Server Proxy: connection [" + serverThreadId + "]: IOException: " + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				logger.info("Server Proxy: connection [" + serverThreadId + "]: IOException: " + e.getMessage());
			}
		}
		logger.info("Server Proxy: closing connection [" + serverThreadId + "]");
	}


    /* gestisce la richiesta del servizio corretto al servente */
    private String executeOperation(String op) throws RemoteException {
        String reply = null;

        if ( op.equals("CONNECT") ) {
            done = false;
            reply = "ACK";
            logger.info("Server Proxy: connection [" + serverThreadId + "]: connect");
        } else if ( op.equals("DISCONNECT") ) {
            done = true;
            reply = "ACK";
            logger.info("Server Proxy: connection [" + serverThreadId + "]: disconnect");
        } else if ( op.equals("getGlobalCounter") ) {
            reply = String.valueOf( service.getGlobalCounter() );
            logger.info("Server Proxy: connection [" + serverThreadId + "]: " +
    				"service.getGlobalCounter()" + " --> " + reply);
        } else if ( op.equals("getSessionCounter") ) {
            reply = String.valueOf( service.getSessionCounter() );
            logger.info("Server Proxy: connection [" + serverThreadId + "]: " +
    				"service.getSessionCounter()" + " --> " + reply);
        } else if ( op.equals("incrementCounter") ) {
        	service.incrementCounter();
            reply = "";
            logger.info("Server Proxy: connection [" + serverThreadId + "]: " +
    				"service.incrementCounter()");
        } else {
            throw new RemoteException("Operation " + op + " is not supported");
        }

        return reply;
    }

}
