package asw.socket.server.connector;

import asw.socket.service.*;

import java.net.*;
import java.io.*;

import java.util.logging.Logger;

/* Remote proxy lato server per il servizio Service. */
public class ServiceServerUDPProxy {
    private Service service;             // il vero servizio
    private int port;                    // porta per il servizio

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.server.connector");

	public ServiceServerUDPProxy(Service service, int port) {
        this.service = service;
        this.port = port;
    }

    public void run() {
    	DatagramSocket socket = null;
        try {
            /* crea il socket su cui ricevere le richieste */
            socket = new DatagramSocket(this.port);
            /* per il server, disabilita il timeout */
            socket.setSoTimeout(0);
            /* crea il buffer per le richieste */
            byte[] buffer = new byte[1000];
            while (true) {
            	getRequestAndSendReply(socket, buffer);
            }
		} catch (SocketException e) {
			logger.info("Server Proxy: Socket Exception: " + e.getMessage());
		} finally {
			if (socket!=null) {
				socket.close();
			}
		}
    }

    /* gestisce una richiesta */
    private void getRequestAndSendReply(DatagramSocket socket, byte[] buffer) {
    	try {
            /* aspetta un datagramma di richiesta */
            DatagramPacket requestPacket =
                new DatagramPacket(buffer, buffer.length);
            socket.receive(requestPacket);    // bloccante
            /* estrai la richiesta dal datagramma di richiesta */
            String request = new String( requestPacket.getData(),
            		                     requestPacket.getOffset(),
            		                     requestPacket.getLength() );
    		logger.info("Server Proxy: received request: " + request);
            /* la richiesta dovrebbe avere la forma "operazione$argomento" 
			 * (ma potrebbe essere malformata) */
            /* estrae operazione e argomento */
            String op = this.getOp(request);
            String arg = this.getParam(request);
            /* invoca il servizio, ottiene il risultato, e calcola la risposta */
            /*
             * la risposta puo' avere le seguenti forme:
             * "#risultato" oppure 
			 * "@messaggio per eccezione di servizio" oppure 
			 * "!messaggio per eccezione remota"
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
    		logger.info("Server Proxy: sending reply: " + reply);

            /* crea il datagramma di risposta */
            /* la risposta ha la forma "risposta" */
            byte[] replyMessage = reply.getBytes();
            DatagramPacket replyPacket =
                new DatagramPacket( replyMessage, replyMessage.length,
                                                      requestPacket.getAddress(),
                                                      requestPacket.getPort() );
            /* invia il datagramma di risposta */
            socket.send(replyPacket);    // non bloccante
		} catch (IOException e) {
			logger.info("Server Proxy: IO Exception: " + e.getMessage());
		}
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

    /* gestisce la richiesta del servizio corretto al servente */
    private String executeOperation(String op, String arg) throws ServiceException, RemoteException {
        String result = null;

        if ( op.equals("alpha") ) {
            result = service.alpha(arg);
        } else if ( op.equals("beta") ) {
            result = service.beta(arg);
        } else {
            throw new RemoteException("Operation " + op + " is not supported");
        }

        return result;
    }

}

