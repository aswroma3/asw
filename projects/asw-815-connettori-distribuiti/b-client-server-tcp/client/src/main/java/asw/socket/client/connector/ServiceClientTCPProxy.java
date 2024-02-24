package asw.socket.client.connector;

import asw.socket.service.*;

import java.net.*;    // per le socket
import java.io.*;     // per i flussi di I/O

import java.util.logging.Logger;

/* Remote proxy lato client per il servizio Service. */
public class ServiceClientTCPProxy implements Service {
    private InetAddress address;    // indirizzo del server
    private int port;               // porta per il servizio

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.client.connector");

	public ServiceClientTCPProxy(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

	/* questo e' proprio il metodo alpha invocato dal client
     * (anche se il client pensa di parlare direttamente con il servizio) */
    public String alpha(String arg) throws ServiceException, RemoteException {
    	return doOperation("alpha", arg);
    }

    /* questo e' proprio il metodo beta invocato dal client
     * (anche se il client pensa di parlare direttamente con il servizio) */
    public String beta(String arg) throws ServiceException, RemoteException {
    	return doOperation("beta", arg);
    }

    /* metodo di supporto per la comunicazione remota */
    private String doOperation(String op, String arg) throws ServiceException, RemoteException {
    	String result = null;
    	Socket socket = null;
        try {
        	/* chiede una connessione al server */
        	socket = new Socket(address, port);    // bloccante
            /* solleva IOException, se si verifica un errore di I/O nella creazione del socket */

        	/* imposta il timeout ad 1 secondo */
            socket.setSoTimeout(1000);

        	/* crea gli oggetti che rappresentano i due canali di comunicazione
        	 * con il server */
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        	/* codifica la richiesta di servizio e i relativi parametri */
            /* la richiesta ha la forma "operazione$parametro" */
			String request = op + "$" + arg;
			/* per simulare una richiesta malformata */
			// if (op.equals("beta") && arg.equals("Alfa")) {
			// 	request = op + "!" + arg;
			// }

			/* invia la richiesta */
            logger.info("Client Proxy: sending request: " + request);
			out.writeUTF(request);    // non bloccante

			/* riceve la risposta */
			String reply = in.readUTF();    // bloccante
       		logger.info("Client Proxy: received reply: " + reply);

            /*
             * elabora la risposta, che puo' avere le seguenti forme:
             * "#risultato" oppure 
			 * "@messaggio per eccezione di servizio" oppure 
			 * "!messaggio per eccezione remota"
             */
        	if (reply.startsWith("#")) {
        		/* e' un risultato */
        		result = reply.substring(1);
        	} else if (reply.startsWith("@")) {
        		/* si e' verificata una ServiceException */
        		String message = reply.substring(1);
        		throw new ServiceException(message);
        	} else if (reply.startsWith("!")) {
        		/* si e' verificata una RemoteException */
        		String message = reply.substring(1);
        		throw new RemoteException(message);
			} else {
        		/* risposta malformata, solleva una RemoteException */
        		throw new RemoteException("Malformed reply: " + reply);
        	}
		} catch (UnknownHostException e) {
			logger.info("Client Proxy: UnknownHostException: " + e.getMessage());
			throw new RemoteException("UnknownHostException: " + e.getMessage());
		} catch (EOFException e) {
			logger.info("Client Proxy: EOFException: " + e.getMessage());
			throw new RemoteException("EOFException: " + e.getMessage());
		} catch (IOException e) {
			logger.info("Client Proxy: IOException: " + e.getMessage());
			throw new RemoteException("IOException: " + e.getMessage());
		} finally {
			if (socket!=null)
				try { socket.close(); }
				catch (IOException e) {
					logger.info("Client Proxy: IOException: " + e.getMessage());
					throw new RemoteException("IOException: " + e.getMessage());
				}
		}
        return result;
    }

}

