from fastapi import FastAPI, HTTPException

from restaurant_service_api_rest.RestaurantRestApi import *

from restaurant_service.ioc_container import Container 

#from dependency_injector import containers, providers
from dependency_injector.wiring import Provide, inject

app = FastAPI()

restaurant_service = Provide[Container.restaurant_service_provider]

def restaurant_to_get_restaurant_response(restaurant): 
    return GetRestaurantResponse(restaurantId=restaurant.rid, name=restaurant.name, location=restaurant.location)

@app.post("/rest/restaurants", status_code=201)
def create_restaurant(request: CreateRestaurantRequest):
    try: 
        restaurant = restaurant_service.create_restaurant(request.name, request.location)
        response = CreateRestaurantResponse(restaurantId=restaurant.rid)
        return response
    except:
        raise HTTPException(status_code=500, detail="Restaurant not created")

@app.get("/rest/restaurants/{restaurantId}")
def get_restaurant(restaurantId: int):
    try: 
        restaurant = restaurant_service.get_restaurant(restaurantId)
        return restaurant_to_get_restaurant_response(restaurant)
    except:
        raise HTTPException(status_code=404, detail="Restaurant not found")

@app.get("/rest/restaurants")
def get_all_restaurants():
    restaurants = restaurant_service.get_all_restaurants()
    restaurant_responses = [ restaurant_to_get_restaurant_response(r) for r in restaurants ]
    return GetRestaurantsResponse(restaurants=restaurant_responses)

@app.get("/rest/restaurants/findByNameAndLocation/")
def get_restaurant_by_name_and_location(name: str, location: str):
    try: 
        restaurant = restaurant_service.get_restaurant_by_name_and_location(name, location)
        return restaurant_to_get_restaurant_response(restaurant)
    except:
        raise HTTPException(status_code=404, detail="Restaurant not found")

@app.get("/rest/restaurants/findByLocation/")
def get_restaurants_by_location(location: str):
    restaurants = restaurant_service.get_restaurants_by_location(location)
    restaurant_responses = [ restaurant_to_get_restaurant_response(r) for r in restaurants ]
    return GetRestaurantsResponse(restaurants=restaurant_responses)
