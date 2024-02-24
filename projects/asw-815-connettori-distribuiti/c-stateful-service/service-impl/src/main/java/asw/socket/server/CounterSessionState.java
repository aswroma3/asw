package asw.socket.server;

/**
 * CounterSessionState rappresenta lo stato della sessione/conversazione con uno specifico client.
 * Verra' allocata un'istanza di CounterSessionState per ciascun client/connessione.
 */
public class CounterSessionState {

	private int sessionCounter = 0;

	public int getSessionCounter() {
		return this.sessionCounter;
	}

	public void incrementSessionCounter() {
		this.sessionCounter++;
	}

}
