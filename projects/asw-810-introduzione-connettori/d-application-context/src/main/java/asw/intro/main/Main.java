package asw.intro.main;

import asw.intro.client.Client;
import asw.intro.context.ApplicationContext;

/* Applicazione client: crea ed avvia il client. */
public class Main {

	/* Crea e avvia un oggetto Client. */
	public static void main(String[] args) {
		Client client = ApplicationContext.getInstance().getClient();
		client.run();
	}

}
