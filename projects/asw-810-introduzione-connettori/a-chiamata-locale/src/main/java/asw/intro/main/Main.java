package asw.intro.main;

import asw.intro.client.Client;

/* Applicazione client: crea ed avvia il client. */
public class Main {

	/* Crea e avvia un oggetto Client. */
	public static void main(String[] args) {
		Client client = new Client();
		client.run();
	}

}
