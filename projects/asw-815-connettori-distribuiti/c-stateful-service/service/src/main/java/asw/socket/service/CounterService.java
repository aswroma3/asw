package asw.socket.service;

/**
 * Interfaccia del servizio CounterService.
 */
public interface CounterService {

    public void incrementCounter() throws RemoteException;

	public int getGlobalCounter() throws RemoteException;

    public int getSessionCounter() throws RemoteException;

}

