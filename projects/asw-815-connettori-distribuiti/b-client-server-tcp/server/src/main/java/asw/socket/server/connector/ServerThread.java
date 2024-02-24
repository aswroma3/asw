package asw.socket.server.connector;

import asw.socket.service.*;

import java.net.*;
import java.io.*;

import java.util.logging.Logger;

public class ServerThread extends Thread {

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.server.connector");

	private Service service;
	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;

	private static int MAX_SERVER_THREAD_ID = 0;
	private int serverThreadId;

	public ServerThread(Socket clientSocket, Service service) {
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
			/* riceve una richiesta */
			String request = in.readUTF();  // bloccante
    		logger.info("Server Proxy: connection [" + serverThreadId + "]: received request: " + request);

            /* la richiesta dovrebbe avere la forma "operazione$argomento" 
			 * (ma potrebbe essere malformata) */
            /* estrae operazione e argomento */
            String op = this.getOp(request);
            String arg = this.getParam(request);

            /* invoca il servizio, ottiene il risultato, e calcola la risposta */
            /*
             * la risposta puo' avere le seguenti forme:
             * "#risultato" oppure "@messaggio per eccezione"
             */
            String reply = null;
            try {
                String result = this.executeOperation(op, arg);
                /* se siamo qui, operazione completata, la risposta ha la forma "#risultato" */
                reply = "#" + result;
    		} catch (ServiceException e) {
                /* se siamo qui, operazione NON completata, la risposta ha la forma "@messaggio" */
                reply = "@" + e.getMessage();
    		} catch (RemoteException e) {
                /* il servente non solleva MAI RemoteException, 
				 * ma si può arrivare qui da executeOperation() 
				 * se la richiesta è malformata */
                reply = "!" + e.getMessage();
            }

    		/* invia la risposta */
    		logger.info("Server Proxy: connection [" + serverThreadId + "]: sending reply: " + reply);
			out.writeUTF(reply);    // non bloccante
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

    /* estrae l'operazione dalla richiesta */
    private String getOp(String request) {
        /* la richiesta dovrebbe avere la forma "operazione$parametro" */
        int sep = request.indexOf("$");
        String op; 
		if (sep>=0) {
			op = request.substring(0,sep); 
		} else {
			/* se manca il $, assume che il parametro sia null */ 
			op = request; 
		}
        return op;
    }

    /* estrae il parametro dalla richiesta */
    private String getParam(String request) {
        /* la richiesta dovrebbe avere la forma "operazione$parametro" */
        int sep = request.indexOf("$");
		String param; 
		if (sep>=0) {
			param = request.substring(sep+1);
		} else {
			/* se manca il $, assume che il parametro sia null */ 
			param = null; 
		}
        return param;
    }

    /* gestisce l'invocazione dell'operazione richiesta al servizio */
    private String executeOperation(String op, String param) throws ServiceException, RemoteException {
        String result = null;

        if ( op.equals("alpha") ) {
            result = service.alpha(param);
        }
        else if ( op.equals("beta") ) {
            result = service.beta(param);
        } else {
            throw new RemoteException("Operation " + op + " is not supported");
        }

        return result;
    }

}
