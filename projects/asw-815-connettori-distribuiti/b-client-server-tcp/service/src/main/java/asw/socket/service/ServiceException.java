package asw.socket.service;

/**
 * L'eccezione ServiceException rappresenta un'eccezione "funzionale" legata al servizio.
 *
 * Ad esempio, la mancata soddisfazione di una pre-condizione.
 * In particolare, una ServiceException puo' essere sollevata direttamente dal servente.
 */
public class ServiceException extends Exception {
	public ServiceException(String message) {
		super(message);
	}
}
