import httpx 
#import urllib

from typing import List
from pydantic import BaseModel

class Restaurant(BaseModel):
	rid: int
	name: str
	location: str 

	def __str__(self): 
		return "Restaurant(rid=" + str(self.rid) + ", name=" + self.name + ", location=" + self.location + ")"

class GetRestaurantResponse(BaseModel):
	restaurantId: int
	name: str
	location: str 

class GetRestaurantsResponse(BaseModel):
	restaurants: List[GetRestaurantResponse]
    
class RestaurantClient:
#	def get_restaurant_as_json(restaurant_id: int): 
#		request_url = 'http://localhost:8080/rest/restaurants/{id}'.format(id=restaurant_id)
#		response = httpx.get(request_url)
#		return response.json()

	def get_restaurant(restaurant_id: int): 
		request_url = 'http://localhost:8080/rest/restaurants/{id}'.format(id=restaurant_id)
		response = httpx.get(request_url)
		restaurant_response = GetRestaurantResponse.parse_obj(response.json())
		restaurant = Restaurant(rid=restaurant_response.restaurantId, name=restaurant_response.name, location=restaurant_response.location)
		return restaurant

#	def get_all_restaurants_as_json(): 
#		request_url = 'http://localhost:8080/rest/restaurants'
#		response = httpx.get(request_url)
#		return response.json()

	def get_all_restaurants(): 
		request_url = 'http://localhost:8080/rest/restaurants'
		response = httpx.get(request_url)
		restaurants_response = GetRestaurantsResponse.parse_obj(response.json())
		restaurants: list[Restaurant] = []
		for restaurant_response in restaurants_response.restaurants: 
			restaurants.append( Restaurant(rid=restaurant_response.restaurantId, name=restaurant_response.name, location=restaurant_response.location) )
		return restaurants
		

