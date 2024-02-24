package asw.intro.client;

import asw.intro.service.Service;
import asw.intro.connector.ServiceFactory;

import java.util.logging.*;

/* Client del servizio. */
public class Client {

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.client");

	/* il servizio Service */
	private Service service;

	/* Crea un nuovo client. */
	public Client() {
		logger.info("Creazione di un nuovo Client");
		this.service = ServiceFactory.getInstance().getService();
	}

	/* L'esecuzione del client, che richiede l'esecuzione del servizio Service. */
	public void run() {
		/* nota: il servizio service.alpha converte una stringa in maiuscole */
		logger.info("Client: Ora uso un servizio che converte una stringa in MAIUSCOLE");

		String[] data = { "ciao", "Prova", "FINE" };

		for (String arg : data) {
			logger.info("Client: calling alpha(" + arg + ")");
			String result = service.alpha(arg);
			logger.info("Client: alpha(" + arg + ") ==> " + result);
		}
	}

}
