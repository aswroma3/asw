package asw.socket.service.impl;

import asw.socket.service.*;

import java.util.logging.Logger;

/**
 * Implementazione del servizio Service.
 * Per semplicita' il logging viene fatto qui nel servizio,
 * ma sarebbe piu' opportuno farlo altrove (in un proxy).
 */
public class ServiceImpl implements Service {

	/* logger */
	private static Logger logger = Logger.getLogger("asw.socket.service.impl");

	/**
	 * Converte in lettere maiuscole la stringa arg.
	 * Tuttavia, e' richiesto che arg sia lunga almeno 4 caratteri.
	 * Se arg e' nulla o lunga meno di 4 caratteri, viene sollevata l'eccezione ServiceException.
	 */
	public String alpha(String arg) throws ServiceException {
		if (arg==null || arg.length()<4) {
			logger.info("ServiceImpl: executing alpha: ServiceException: argument is null or too short.");
			throw new ServiceException("ServiceImpl: executing alpha: ServiceException: argument is null or too short.");
		} else {
			String result = arg.toUpperCase();
			logger.info("ServiceImpl: executing alpha(" + arg + ") ==> " + result);
			return result;
		}
    }

	/**
	 * Converte in lettere minuscole la stringa arg.
	 * Se arg e' nulla, viene sollevata l'eccezione ServiceException.
	 *
	 * Tuttavia, introduce un ritardo pari a len/10 secondi, dove len e' la lunghezza di arg.
	 * Si noti che questa e' una caratteristica dell'implementazione, non dell'interfaccia.
	 * Si noti anche che questo potrebbe causare un timeout nell'accesso remoto, se arg fosse troppo lunga.
	 */
	public String beta(String arg) throws ServiceException {
		if (arg==null) {
			logger.info("ServiceImpl: executing beta: ServiceException: argument is null.");
			throw new ServiceException("ServiceImpl: executing beta: ServiceException: argument is null.");
		} else {
			String result = arg.toLowerCase();
			logger.info("ServiceImpl: executing beta(" + arg + ") ==> " + result);
			/* introduce un ritardo proporzionale alla lunghezza del parametro */
			sleep(arg.length()*100);
	    	return result;
		}
    }

	/* Introduce un ritardo di esattamente delay millisecondi. */
	public static void sleep(int delay) {
        try {
        	// int delay = (int)(Math.random()*maxDelay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}	
	
}

