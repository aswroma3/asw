#
# Server gRPC per Restaurant 
#

import grpc

import restaurant_service_api_grpc.RestaurantService_pb2 as RestaurantService_pb2
import restaurant_service_api_grpc.RestaurantService_pb2_grpc as RestaurantService_pb2_grpc

from concurrent import futures

# parameters 

restaurant_grpc_port = 50051 

def restaurant_to_get_restaurant_reply(restaurant): 
    return RestaurantService_pb2.GetRestaurantReply(restaurantId=restaurant.rid, name=restaurant.name, location=restaurant.location)

class RestaurantServiceServicer(RestaurantService_pb2_grpc.RestaurantServiceServicer):
    def __init__(self, restaurant_service):
        self.restaurant_service = restaurant_service
    
    def createRestaurant(self, request, context):
        name = request.name
        location = request.location
        try: 
            restaurant = self.restaurant_service.create_restaurant(name, location)
            return RestaurantService_pb2.CreateRestaurantReply(restaurantId=restaurant.rid)
        except: 
            context.set_code(grpc.StatusCode.ALREADY_EXISTS)
            context.set_details("Restaurant not created")
            return RestaurantService_pb2.CreateRestaurantReply()

    def getRestaurantById(self, request, context):
        try: 
            restaurantId = request.restaurantId
            restaurant = self.restaurant_service.get_restaurant(restaurantId)
            return RestaurantService_pb2.GetRestaurantReply(restaurantId=restaurant.rid, name=restaurant.name, location=restaurant.location)
        except: 
            context.set_code(grpc.StatusCode.NOT_FOUND)
            context.set_details("Unable to find restaurant")
            return RestaurantService_pb2.GetRestaurantReply()
        
    def getAllRestaurants(self, request, context):
        restaurants = self.restaurant_service.get_all_restaurants()
        restaurant_replies = [ restaurant_to_get_restaurant_reply(r) for r in restaurants ]
        return RestaurantService_pb2.GetRestaurantsReply(restaurants=restaurant_replies)        

    def getRestaurantByNameAndLocation(self, request, context):
        try: 
            name = request.name
            location = request.location
            restaurant = self.restaurant_service.get_restaurant_by_name_and_location(name, location)
            return RestaurantService_pb2.GetRestaurantReply(restaurantId=restaurant.rid, name=restaurant.name, location=restaurant.location)
        except: 
            context.set_code(grpc.StatusCode.NOT_FOUND)
            context.set_details("Restaurant not found")
            return RestaurantService_pb2.GetRestaurantReply()
        
    def getRestaurantsByLocation(self, request, context):
        location = request.location
        restaurants = self.restaurant_service.get_restaurants_by_location(location)
        restaurant_replies = [ restaurant_to_get_restaurant_reply(r) for r in restaurants ]
        return RestaurantService_pb2.GetRestaurantsReply(restaurants=restaurant_replies)        

class RestaurantGrpcServer: 
    def __init__(self, restaurant_service): 
        self.restaurant_service = restaurant_service

    def start(self):
        server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
        RestaurantService_pb2_grpc.add_RestaurantServiceServicer_to_server(
            RestaurantServiceServicer(self.restaurant_service), server
        )
        server.add_insecure_port("[::]:{}".format(restaurant_grpc_port))
        server.start()
        server.wait_for_termination()

