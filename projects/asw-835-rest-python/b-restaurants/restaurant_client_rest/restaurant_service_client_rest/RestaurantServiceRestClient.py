import httpx 

from restaurant_client_rest.domain.Restaurant import Restaurant

from restaurant_service_api_rest.RestaurantRestApi import *

class RestaurantServiceException(Exception):
    def __init__(self, message):
        self.message = message
        super().__init__(self.message)

class RestaurantServiceRestClient:
    def __init__(self): 
        pass

    def create_restaurant(self, name: str, location: str): 
        try: 
            create_restaurant_request = CreateRestaurantRequest(name=name, location=location)
            request_url = 'http://localhost:8080/rest/restaurants'
            headers = { 'Content-Type': 'Application/json' }
            response = httpx.post(request_url, data=create_restaurant_request.json(), headers=headers)
            response.raise_for_status()
            create_restaurant_response = CreateRestaurantResponse.parse_obj(response.json())
            return create_restaurant_response.restaurantId
        except Exception as e: 
            raise RestaurantServiceException("Restaurant not created, with exception: " + str(e))

    def get_restaurant(self, restaurant_id: int): 
        try: 
            request_url = 'http://localhost:8080/rest/restaurants/{rid}'.format(rid=restaurant_id)
            response = httpx.get(request_url)
            response.raise_for_status()
            restaurant_response = GetRestaurantResponse.parse_obj(response.json())
            restaurant = Restaurant(rid=restaurant_response.restaurantId, name=restaurant_response.name, location=restaurant_response.location)
            return restaurant
        except Exception as e: 
            raise RestaurantServiceException("Restaurant not found, with exception: " + str(e))

    def get_all_restaurants(self): 
        request_url = 'http://localhost:8080/rest/restaurants'
        response = httpx.get(request_url)
        restaurants_response = GetRestaurantsResponse.parse_obj(response.json())
        restaurants: list[Restaurant] = []
        for restaurant_response in restaurants_response.restaurants: 
            restaurants.append( Restaurant(rid=restaurant_response.restaurantId, name=restaurant_response.name, location=restaurant_response.location) )
        return restaurants
		
    def get_restaurant_by_name_and_location(self, name: str, location: str): 
        try: 
            request_url = 'http://localhost:8080/rest/restaurants/findByNameAndLocation/?name={name}&location={location}'.format(name=name, location=location)
            response = httpx.get(request_url)
            response.raise_for_status()
            restaurant_response = GetRestaurantResponse.parse_obj(response.json())
            restaurant = Restaurant(rid=restaurant_response.restaurantId, name=restaurant_response.name, location=restaurant_response.location)
            return restaurant
        except Exception as e: 
            raise RestaurantServiceException("Restaurant not found, with exception: " + str(e))

    def get_restaurants_by_location(self, location: str): 
        request_url = 'http://localhost:8080/rest/restaurants/findByLocation/?location={location}'.format(location=location)
        response = httpx.get(request_url)
        restaurants_response = GetRestaurantsResponse.parse_obj(response.json())
        restaurants: list[Restaurant] = []
        for restaurant_response in restaurants_response.restaurants: 
            restaurants.append( Restaurant(rid=restaurant_response.restaurantId, name=restaurant_response.name, location=restaurant_response.location) )
        return restaurants
		

