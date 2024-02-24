package asw.socket.server;

/**
 * CounterApplicationState rappresenta lo stato globale del servizio CounterService.
 * Si tratta di un singleton, condiviso da tutti i client.
 */
public class CounterApplicationState {

	/* singleton */
	private static CounterApplicationState instance = null;

    public static synchronized CounterApplicationState getInstance() {
        if (instance==null) instance = new CounterApplicationState();
        return instance;
    }

	private int globalCounter = 0;

	public int getGlobalCounter() {
		return this.globalCounter;
	}

	public void incrementGlobalCounter() {
		this.globalCounter++;
	}

}
