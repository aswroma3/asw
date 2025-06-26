#
# Simple Producer 
#

from restaurant_service_api_message.RestaurantCommandApi import CreateRestaurantCommand, create_restaurant_command_type 

import random

import logging 

logging.basicConfig()
logger = logging.getLogger(__name__)
logger.setLevel(level=logging.INFO)

# parameters 

producer_name = 'Python Restaurant Command Publisher'

# random names and locations

NAMES = [ "Da Pino", "Pasta e Amore", "Trattoria Rustica", "Ristorante al Mattone", "Antichi Sapori", "Iyo Omakase" ]
LOCATIONS = [ "Roma", "Milano", "Torino", "Palermo", "Napoli", "Firenze" ]

def random_name(): 
    i = random.randint(0, len(NAMES)-1)
    return NAMES[i]

def random_location(): 
    i = random.randint(0, len(LOCATIONS)-1)
    return LOCATIONS[i]

class RestaurantCommandPublisherRunner: 
    
    def __init__(self, simple_message_producer): 
        self.simple_message_producer = simple_message_producer

    def short_type(self, my_type): 
        return my_type[my_type.rfind('.')+1:]

    def run(self):
        name = random_name()
        location = random_location()
        command = CreateRestaurantCommand(name=name, location=location)
        command_type = create_restaurant_command_type
        logger.info('{} sending command: {} {}'.format(producer_name, self.short_type(command_type), command))
        self.simple_message_producer.publish(command, command_type)

        name = random_name()
        location = random_location()
        command = CreateRestaurantCommand(name=name, location=location)
        command_type = create_restaurant_command_type
        logger.info('{} sending command: {} {}'.format(producer_name, self.short_type(command_type), command))
        self.simple_message_producer.publish(command, command_type)

        self.simple_message_producer.close()
