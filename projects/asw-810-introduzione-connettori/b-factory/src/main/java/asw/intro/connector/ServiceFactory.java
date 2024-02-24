package asw.intro.connector;

import asw.intro.service.Service;
import asw.intro.server.ServiceImpl;

import java.util.logging.Logger;

/* Factory per il servizio Service. */
public class ServiceFactory {

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.connector");

    /* il singleton per la factory */
	private static ServiceFactory instance = null;

	/* il riferimento al servizio */
	private Service service = null;

	/* Costruttore della factory (privato per singleton). */
	private ServiceFactory() {
		logger.info("Creazione della ServiceFactory");
	}

    public static synchronized ServiceFactory getInstance() {
        if (instance==null) {
        	instance = new ServiceFactory();
        }
        return instance;
    }

    /* Factory method per il servizio Service. */
    public synchronized Service getService() {
		logger.info("ServiceFactory: getService()");
    	if (service==null) {
    		service = new ServiceImpl();
    	}
        return service;
    }

}

