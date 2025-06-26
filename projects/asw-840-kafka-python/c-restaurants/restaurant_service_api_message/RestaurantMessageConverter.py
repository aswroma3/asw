#
# Si occupa di convertire da messaggi in json a oggetti che li rappresentano 
#

from .RestaurantCommandApi import CreateRestaurantCommand, create_restaurant_command_type 
from .RestaurantEventApi import RestaurantCreatedEvent, restaurant_created_event_type 

class RestaurantMessageConverter: 
    def json_to_object(json_data, data_type):
        obj = None 
        if data_type == create_restaurant_command_type: 
            obj = CreateRestaurantCommand.model_validate_json(json_data)
        elif data_type == restaurant_created_event_type: 
            obj = RestaurantCreatedEvent.model_validate_json(json_data)
        return obj     