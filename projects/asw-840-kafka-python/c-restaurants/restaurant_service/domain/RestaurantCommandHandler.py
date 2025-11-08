#
# Gestore dei comandi 
#

from restaurant_service_api_message.RestaurantCommandApi import CreateRestaurantCommand

consumer_name = 'RestaurantCommandHandler'

class RestaurantCommandHandler: 
    def __init__(self, restaurant_service): 
        # print("Creating RestaurantCommandHandler")
        self.restaurant_service = restaurant_service
        # pass 

    def onCommand(self, command): 
        # riceve un oggetto comando  
        print("{} received command: {} {}".format(consumer_name, type(command), command))
        
        if isinstance(command, CreateRestaurantCommand): 
            self.create_restaurant(command)
        else: 
            print("UNKNOWN COMMAND: " + str(command))
            
    def create_restaurant(self, command): 
        try: 
            self.restaurant_service.create_restaurant(command.name, command.location)
        except Exception as e: 
            print("COMMAND: " + str(command) + " THREW " + str(e))
