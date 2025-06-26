#
# Client del servizio Restaurant 
#

import random

import logging 

logging.basicConfig()
logger = logging.getLogger(__name__)
logger.setLevel(level=logging.INFO)

NAMES = [ "Da Pino", "Pasta e Amore", "Trattoria Rustica", "Ristorante al Mattone", "Antichi Sapori", "Iyo Omakase" ]
LOCATIONS = [ "Roma", "Milano", "Torino", "Palermo", "Napoli", "Firenze" ]

def random_name(): 
    i = random.randint(0, len(NAMES)-1)
    return NAMES[i]

def random_location(): 
    i = random.randint(0, len(LOCATIONS)-1)
    return LOCATIONS[i]

class RestaurantClientRunner: 

    def __init__(self, restaurant_service_client): 
        self.restaurant_service_client = restaurant_service_client
    
    def run(self):
#        restaurant_service_client = ioc_container.restaurant_service_client_provider()
        
        logger.info("*** trova il primo ristorante ***") 
        try: 
            r = self.restaurant_service_client.get_restaurant(1) 
            logger.info(r)
        except: 
            logger.info("Si è verificata un'eccezione")

        logger.info("*** trova tutti i ristoranti ***") 
        rr = self.restaurant_service_client.get_all_restaurants() 
        logger.info(rr) 

        logger.info("*** crea un nuovo ristorante ***")  
        try: 
            name = random_name() 
            location = random_location() 
            newr = self.restaurant_service_client.create_restaurant(name, location) 
            logger.info(newr) 
            logger.info("*** trova il ristorante appena creato ***") 
            r = self.restaurant_service_client.get_restaurant_by_name_and_location(name, location) 
            logger.info(r) 
        except: 
            logger.info("Si è verificata un'eccezione")

        logger.info("*** trova tutti i ristoranti di Roma ***")  
        rr = self.restaurant_service_client.get_restaurants_by_location("Roma") 
        logger.info(rr) 
