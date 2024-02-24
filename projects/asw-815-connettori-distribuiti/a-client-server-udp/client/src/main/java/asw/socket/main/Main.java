package asw.socket.main;

import asw.socket.client.Client;
import asw.socket.context.ApplicationContext;

/* Applicazione che ottiene ed avvia il client. */
public class Main {

	/* Ottiene e avvia un oggetto Client. */
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
