#
# Servizio per elaborare i messaggi ricevuti 
#

from restaurant_service_api_message.RestaurantEventApi import RestaurantCreatedEvent, restaurant_created_event_type 

from .Restaurant import Restaurant 

import logging 

logging.basicConfig()
logger = logging.getLogger(__name__)
logger.setLevel(level=logging.INFO)

# Parameters 

consumer_name = 'Python Restaurant Event Listener'

class RestaurantEventListener: 
    def __init__(self): 
        pass 

    def short_type(self, my_type): 
        return my_type[my_type.rfind('.')+1:]

    def onMessage(self, event, event_type): 
        # riceve un oggetto evento  
        logger.info("{} received event: {} {}".format(consumer_name, self.short_type(event_type), event))

        if isinstance(event, RestaurantCreatedEvent): 
            restaurant = Restaurant(rid=event.id, name=event.name, location=event.location)
            logger.info("CREATED RESTAURANT: " + str(restaurant))
        else: 
            logger.info("UNKNOWN EVENT: " + str(event))

#    def start(self): 
#        pass
#        # self.simple_message_consumer.start()
        


