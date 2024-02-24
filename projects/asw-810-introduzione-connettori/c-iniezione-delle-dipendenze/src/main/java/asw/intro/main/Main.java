package asw.intro.main;

import asw.intro.client.Client;
import asw.intro.service.Service;
import asw.intro.connector.ServiceFactory;

/* Applicazione client: crea ed avvia il client. */
public class Main {

	/* Crea e avvia un oggetto Client. */
	public static void main(String[] args) {
		Service service = ServiceFactory.getInstance().getService();
		Client client = new Client();
		/* iniezione della dipendenza service */
		client.setService(service);
		/* avvia il client */
		client.run();
	}

}
