package asw.socket.client.connector;

import asw.socket.service.*;

import java.net.*;    // per le socket
import java.io.*;     // per i flussi di I/O

import java.util.*;

import java.util.logging.Logger;

/* remote proxy lato client per il servizio */
public class ServiceClientTCPProxy implements RemoteCounterService {

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.client.connector");

    private InetAddress address;    // indirizzo del server
    private int port;               // porta per il servizio

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

    public ServiceClientTCPProxy(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

	/* incrementa il contatore */
    public void incrementCounter() throws RemoteException {
    	doOperation("incrementCounter");
    }

    /* restituisce il contatore globale */
    public int getGlobalCounter() throws RemoteException {
    	return new Scanner( doOperation("getGlobalCounter") ).nextInt();
    }

    /* restituisce il contatore di sessione */
    public int getSessionCounter() throws RemoteException {
    	return new Scanner( doOperation("getSessionCounter") ).nextInt();
    }

    /* avvia una nuova sessione */
    public void connect() throws RemoteException {
        try {
        	this.socket = new Socket(address, port);    // bloccante
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
        	/* imposta il timeout (in ms) */
            // socket.setSoTimeout(2000);
		} catch (IOException e) {
			logger.info("Client Proxy: connect: IO Exception: " + e.getMessage());
			throw new RemoteException("IO Exception: " + e.getMessage());
		}
    	doOperation("CONNECT");
    }

    /* chiude la sessione */
    public void disconnect() throws RemoteException {
    	doOperation("DISCONNECT");
		try {
			socket.close();
		} catch (IOException e) {
			logger.info("Client Proxy: disconnect: IO Exception: " + e.getMessage());
			throw new RemoteException("IO Exception: " + e.getMessage());
		}
    }

    /* metodo di supporto per la comunicazione remota */
    private String doOperation(String op) throws RemoteException {
    	String result = null;
        try {
        	/* codifica la richiesta di servizio ed i relativi parametri */
            /* la richiesta ha la forma "operazione" */
			String request = op;

			/* invia la richiesta */
            logger.info("Client Proxy: sending request: " + request);
			out.writeUTF(request);    // non bloccante

			/* riceve la risposta */
			String reply = in.readUTF();    // bloccante
       		logger.info("Client Proxy: received reply: " + reply);

            /* estrae la risposta, che in questo caso ha la forma "risultato" */
            result = reply;
		} catch (UnknownHostException e) {
			logger.info("Server Proxy: UnknownHostException: " + e.getMessage());
			throw new RemoteException("UnknownHostException: " + e.getMessage());
		} catch (EOFException e) {
			logger.info("Server Proxy: EOFException: " + e.getMessage());
			throw new RemoteException("EOFException: " + e.getMessage());
		} catch (IOException e) {
			logger.info("Server Proxy: IOException: " + e.getMessage());
			throw new RemoteException("IOException: " + e.getMessage());
		}
        return result;
    }

}

