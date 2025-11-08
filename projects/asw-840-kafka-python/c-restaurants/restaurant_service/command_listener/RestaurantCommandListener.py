#
# Servizio per elaborare i messaggi ricevuti 
#

# Parameters 

consumer_name = 'Python Restaurant Command Listener'

class RestaurantCommandListener: 
    def __init__(self, restaurant_command_handler): 
        print("Creating RestaurantCommandListener")
        self.consumer_name = consumer_name 
        self.restaurant_command_handler = restaurant_command_handler

    def short_type(self, my_type): 
        return my_type[my_type.rfind('.')+1:]

    def onMessage(self, command, command_type): 
        # riceve un oggetto comando  
        # print("{} received command: {} {}".format(self.consumer_name, self.short_type(command_type), command))
        self.restaurant_command_handler.onCommand(command) 

