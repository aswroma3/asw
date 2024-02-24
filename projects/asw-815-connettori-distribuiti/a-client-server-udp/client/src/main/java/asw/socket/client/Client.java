package asw.socket.client;

import asw.socket.service.*;

import java.util.logging.Logger;

/* Client del servizio. */
public class Client {

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.client");

	/* il servizio Service */
	private Service service;

	/* Crea un nuovo client. */
	public Client() {
		logger.info("Creazione di un nuovo Client");
	}

	/* Setter per il servizio Service. */
	public void setService(Service service) {
		logger.info("Client: setService()");
		this.service = service;
	}

	/* richiede il servizio */
	public void run() {
		logger.info("Client: Ora uso il servizio Service");

		/* dovrebbe restituire ALFABETO */
		logger.info("");
		logger.info("*** deve restituire ALFABETO");
		callAlpha("Alfabeto");

		/* dovrebbe sollevare una ServiceException */
		logger.info("");
		logger.info("*** deve sollevare una ServiceException");
		callAlpha("Az");

		/* dovrebbe restituire alfa */
		logger.info("");
		logger.info("*** deve restituire alfa");
		callBeta("Alfa");

		/* dovrebbe sollevare una RemoteException */
		logger.info("");
		logger.info("*** deve sollevare una RemoteException");
		callBeta("AbcDefGhiLmnOpqRstUvz");

		logger.info("");
		logger.info("Client: Ho finito di usare il servizio Service");
	}

	private void callAlpha(String arg) {
		try {
			logger.info("Client: calling alpha(" + arg + ")");
			String result = service.alpha(arg);
			logger.info("Client: alpha(" + arg + ") ==> " + result);
		} catch (ServiceException e) {
			logger.info("Client: ServiceException while executing alpha(" + arg + ") ==> " + e.getMessage());
		} catch (RemoteException e) {
			logger.info("Client: RemoteException while executing alpha(" + arg + ") ==> " + e.getMessage());
		}
	}

	private void callBeta(String arg) {
		try {
			logger.info("Client: calling beta(" + arg + ")");
			String result = service.beta(arg);
			logger.info("Client: beta(" + arg + ") ==> " + result);
		} catch (ServiceException e) {
			logger.info("Client: ServiceException while executing beta(" + arg + ") ==> " + e.getMessage());
		} catch (RemoteException e) {
			logger.info("Client: RemoteException while executing beta(" + arg + ") ==> " + e.getMessage());
		}

	}

}
