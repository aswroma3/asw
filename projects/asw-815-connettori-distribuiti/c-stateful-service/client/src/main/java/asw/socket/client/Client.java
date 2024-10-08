package asw.socket.client;

import asw.socket.service.*;

import java.util.logging.Logger;

/* client del servizio */
public class Client {

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.client");

	/* il servizio */
	private RemoteCounterService service;
	
	/* numero di chiamate da fare */ 
	private static final int NUM_CALLS = 25; 
	
	/* ritardo da introdurre */ 
	private static final int MAX_DELAY = 1000; 
	private static final int MIN_DELAY = MAX_DELAY/2; 

	/* crea un nuovo oggetto Client */
	public Client() {
	}

	/* setta il servizio per il Client */
	public void setService(RemoteCounterService service) {
		this.service = service;
	}

	/* richiede il servizio */
	public void run() {
		try {
			logger.info("Client: Ora uso il servizio CounterService");

			logger.info("Client: connect()");
			service.connect();

			for (int i=0; i<NUM_CALLS; i++) {
				logger.info("Client: calling service.incrementCounter()");
				service.incrementCounter();
				logger.info("Client: calling service.getGlobalCounter()");
				int globalCount = service.getGlobalCounter();
				logger.info("Client: calling service.getSessionCounter()");
				int sessionCount = service.getSessionCounter();
				logger.info("Client: session count = " + sessionCount + ", global count = " + globalCount);
				/* introduce un piccolo ritardo casuale */
				randomSleep(MIN_DELAY, MAX_DELAY);
			}

			logger.info("Client: disconnect()");
			service.disconnect();

			logger.info("Client: Ho finito di usare il servizio CounterService");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/* Introduce un ritardo casuale, compreso tra minDelay e maxDelay millisecondi. */
	public static void randomSleep(int minDelay, int maxDelay) {
    	int delay = (int)(minDelay + Math.random()*(maxDelay-minDelay));
    	sleep(delay);
	}

	/* Introduce un ritardo di esattamente delay millisecondi. */
	public static void sleep(int delay) {
        try {
        	// int delay = (int)(Math.random()*maxDelay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}
	
}