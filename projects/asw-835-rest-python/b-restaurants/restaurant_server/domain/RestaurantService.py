#
# Implementazione del servizio Restaurant 
#

from .Restaurant import Restaurant

import logging 

logging.basicConfig()
logger = logging.getLogger(__name__)
logger.setLevel(level=logging.INFO)

class RestaurantServiceException(Exception):
    def __init__(self, message):
        self.message = message
        super().__init__(self.message)
        
class RestaurantService: 
    def __init__(self): 
        self.restaurants = {} 
        self.restaurants_by_name_and_location = {}
        self.max_id = 0
        self.init_restaurants() 
        
    def init_restaurants(self): 
        self.create_restaurant("Hostaria dell'Orso", "Roma")
        self.create_restaurant("Baffetto", "Roma")
        self.create_restaurant("L'Omo", "Roma")
        self.create_restaurant("Seta", "Milano")
    
    def create_restaurant(self, name: str, location: str):
        logger.info("create_restaurant({}, {})".format(name, location))
        if name+"+++"+location in self.restaurants_by_name_and_location:
            err_msg = "Unable to create restaurant with " + name + " and " + location
            logger.info("create_restaurant({}, {}) -> RestaurantServiceException({})".format(name, location, err_msg))
            raise RestaurantServiceException(err_msg)
        self.max_id += 1
        restaurant = Restaurant(rid=self.max_id, name=name, location=location)
        self.restaurants[restaurant.rid] = restaurant
        self.restaurants_by_name_and_location[restaurant.name+"+++"+restaurant.location] = restaurant
        logger.info("create_restaurant({}, {}) -> {}".format(name, location, str(restaurant)))
        return restaurant 

    def get_restaurant(self, rid: int): 
        logger.info("get_restaurant({})".format(rid))
        try: 
            restaurant = self.restaurants[rid] 
            logger.info("get_restaurant({}) -> {}".format(rid, restaurant))
            return restaurant 
        except: 
            err_msg = "Unable to find restaurant with " + str(rid)
            logger.info("get_restaurant({}) -> RestaurantServiceException({})".format(rid, err_msg))
            raise RestaurantServiceException(err_msg)
            
    def get_all_restaurants(self): 
        logger.info("get_all_restaurants()")
        restaurants = list(self.restaurants.values()) 
        logger.info("get_all_restaurants() -> {}".format(restaurants))
        return restaurants

    def get_restaurant_by_name_and_location(self, name: str, location: str): 
        logger.info("get_restaurant_by_name_and_location({}, {})".format(name, location))
        try: 
            restaurant = self.restaurants_by_name_and_location[name+"+++"+location] 
            logger.info("get_restaurant_by_name_and_location({}, {}) -> {}".format(name, location, restaurant))
            return restaurant 
        except: 
            err_msg = "Unable to find restaurant with " + name + " and " + location
            logger.info("get_restaurant_by_name_and_location({}, {}) -> RestaurantServiceException({})".format(name, location, err_msg))
            raise RestaurantServiceException(err_msg)

    def get_restaurants_by_location(self, location: str): 
        logger.info("get_restaurants_by_location({})".format(location))
        restaurants = []
        for r in self.restaurants.values(): 
            if location==r.location: 
                restaurants.append(r)
        logger.info("get_restaurants_by_location({}) -> {}".format(location, restaurants))
        return restaurants

