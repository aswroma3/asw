#!/usr/bin/python3

from restaurant_python_client.restaurant_rest_python_client import RestaurantClient

# sample restaurant client 

## trova il primo ristorante 
#rj = RestaurantClient.get_restaurant_as_json(1) 
#print(rj) 

# trova il primo ristorante 
r = RestaurantClient.get_restaurant(1) 
print(r) 

## trova tutti i ristoranti 
#rrj = RestaurantClient.get_all_restaurants_as_json() 
#print(rrj) 

# trova tutti i ristoranti 
rr = RestaurantClient.get_all_restaurants() 
print(rr) 

# trova l'ultimo ristorante 
last_id = rr[-1].rid 
last_r = RestaurantClient.get_restaurant(last_id) 
print(last_r)

