#
# Client gRPC per il servizio Restaurant 
#

from restaurant_client_grpc.domain.Restaurant import Restaurant 

import grpc
import restaurant_service_api_grpc.RestaurantService_pb2 as RestaurantService_pb2
import restaurant_service_api_grpc.RestaurantService_pb2_grpc as RestaurantService_pb2_grpc

# parameters 

restaurant_grpc_port = 50051 

class RestaurantServiceGrpcClient: 
    def __init__(self): 
        channel = grpc.insecure_channel("localhost:{}".format(restaurant_grpc_port))
        self.stub = RestaurantService_pb2_grpc.RestaurantServiceStub(channel)
        
    def create_restaurant(self, name: str, location: str): 
        response = self.stub.createRestaurant(RestaurantService_pb2.CreateRestaurantRequest(name=name, location=location))
        restaurantId = response.restaurantId 
        return restaurantId

    def get_restaurant(self, restaurant_id: int): 
        response = self.stub.getRestaurantById(RestaurantService_pb2.GetRestaurantByIdRequest(restaurantId=restaurant_id))
        restaurant = Restaurant(rid=response.restaurantId, name=response.name, location=response.location)
        return restaurant

    def get_all_restaurants(self): 
        response = self.stub.getAllRestaurants(RestaurantService_pb2.GetAllRestaurantsRequest())
        restaurants: list[Restaurant] = []
        for restaurant_response in response.restaurants: 
            restaurants.append( Restaurant(rid=restaurant_response.restaurantId, name=restaurant_response.name, location=restaurant_response.location) )
        return restaurants
		
    def get_restaurant_by_name_and_location(self, restaurant_name: str, restaurant_location: str): 
        response = self.stub.getRestaurantByNameAndLocation(RestaurantService_pb2.GetRestaurantByNameAndLocationRequest(name=restaurant_name, location=restaurant_location))
        restaurant = Restaurant(rid=response.restaurantId, name=response.name, location=response.location)
        return restaurant

    def get_restaurants_by_location(self, location: str): 
        response = self.stub.getRestaurantsByLocation(RestaurantService_pb2.GetRestaurantsByLocationRequest(location=location))
        restaurants: list[Restaurant] = []
        for restaurant_response in response.restaurants: 
            restaurants.append( Restaurant(rid=restaurant_response.restaurantId, name=restaurant_response.name, location=restaurant_response.location) )
        return restaurants
		
