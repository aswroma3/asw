package asw.socket.service;

/**
 * Interfaccia del servizio Service.
 *
 * Per il significato delle eccezioni ServiceException e RemoteException,
 * si veda la relativa documentazione.
 */
public interface Service {

	/**
	 * Converte in lettere maiuscole la stringa arg.
	 * Tuttavia, e' richiesto che arg sia lunga almeno 4 caratteri.
	 * Se arg e' nulla o lunga meno di 4 caratteri, viene sollevata l'eccezione ServiceException.
	 */
    public String alpha(String arg) throws ServiceException, RemoteException;

    /**
	 * Converte in lettere minuscole la stringa arg.
	 * Se arg e' nulla, viene sollevata l'eccezione ServiceException.
	 */
    public String beta(String arg) throws ServiceException, RemoteException;

}

