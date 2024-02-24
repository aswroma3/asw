package asw.socket.service;

import asw.socket.service.RemoteException;

/**
 * Operazioni aggiuntive per servizi remoti di tipo connection-oriented.
 */
public interface ConnectionOrientedService {

	public void connect() throws RemoteException;

	public void disconnect() throws RemoteException;

}
