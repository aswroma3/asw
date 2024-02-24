package asw.socket.main;

import asw.socket.client.Client;
import asw.socket.context.ApplicationContext;

/* Applicazione client: crea ed avvia il client. */
public class Main {

	/* Crea e avvia un oggetto Client. */
	public static void main(String[] args) {
		Client client;
		if (args.length>0) {
			String serverHost = args[0];
			client = ApplicationContext.getInstance().getClient(serverHost);
		} else {
			client = ApplicationContext.getInstance().getClient();
		}
		client.run();
	}

}
