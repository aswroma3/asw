#
# Simple Producer 
#

from restaurant_service_api_message.RestaurantEventApi import RestaurantCreatedEvent, restaurant_created_event_type 

import logging

# parameters 

producer_name = 'Python Restaurant Event Publisher'

class RestaurantEventPublisher: 
    def __init__(self, simple_message_producer): 
        self.simple_message_producer = simple_message_producer

    def publish(self, event, event_type):
        # print('{} publishing event: {} {}'.format(producer_name, event_type, event))
        self.simple_message_producer.publish(event, event_type)
        
    def close(self): 
        self.simple_message_producer.close()
