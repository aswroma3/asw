package asw.socket.service;

/**
 * L'eccezione RemoteException indica un problema nell'accesso remoto al servizio Service.
 *
 * Ad esempio, la non disponibilita' del servizio o una timeout nel suo accesso.
 * In particolare, si assume che il server NON sollevi MAI una RemoteException,
 * mentre il proxy lato client puo' sollevare una RemoteException se non riesce
 * a comunicare con il server.
 */
public class RemoteException extends Exception {
	public RemoteException(String message) {
		super(message);
	}
}
