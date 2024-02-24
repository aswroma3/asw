package asw.efood.samplerestaurantclient.restaurantservice.rest;

import asw.efood.samplerestaurantclient.domain.*; 
import asw.efood.restaurantservice.api.rest.*; 

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.BodyInserters; 

import java.util.logging.Logger;

import java.util.*; 
import java.util.stream.*; 

@Service
public class RestaurantClientRestAdapter implements RestaurantClientPort {

    private Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${asw.efood.restaurantservice.rest.uri}")
    private String restaurantServiceUri;

    private WebClient webClient;

    public RestaurantClientRestAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(restaurantServiceUri).build();
    }

    public Long createRestaurant(String name, String location) {
        logger.info("Creating restaurant " + name);
		Long restaurantId = null; 
        String restaurantUri = restaurantServiceUri + "/restaurants";
        logger.info("Creating " + restaurantUri + " with " + name + " and " + location);
		CreateRestaurantRequest request = new CreateRestaurantRequest(name, location); 
        Mono<CreateRestaurantResponse> response = webClient
                .post()
                .uri(restaurantUri)
				.body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(CreateRestaurantResponse.class);
        try {
            CreateRestaurantResponse crr = response.block();
            if (crr != null) {
				restaurantId = crr.getRestaurantId(); 
                logger.info("Restaurant created with: " + restaurantId);
            } else {
                logger.info("Restaurant not created");
            }
        } catch (WebClientException e) {
            logger.info("Restaurant not created, with exception " + e.getMessage());
        }
		return restaurantId; 
    }

    public Restaurant getRestaurant(Long restaurantId) {
        logger.info("Looking for restaurant with " + restaurantId);
        Restaurant restaurant = null;
        String restaurantUri = restaurantServiceUri + "/restaurants/{restaurantId}";
        logger.info("Looking for " + restaurantUri + " with " + restaurantId);
        Mono<GetRestaurantResponse> response = webClient
                .get()
                .uri(restaurantUri, restaurantId)
                .retrieve()
                .bodyToMono(GetRestaurantResponse.class);
        try {
            GetRestaurantResponse grr = response.block();
            if (grr != null) {
				restaurant = getRestaurantResponseToRestaurant(grr);
                logger.info("Restaurant found: " + restaurant.toString());
            } else {
                logger.info("Restaurant not found");
            }
        } catch (WebClientException e) {
            logger.info("Restaurant not found, with exception " + e.getMessage());
        }
		return restaurant; 
    }

	private Restaurant getRestaurantResponseToRestaurant(GetRestaurantResponse r) {
		return new Restaurant(r.getRestaurantId(), r.getName(), r.getLocation());
	}

    public List<Restaurant> getAllRestaurants() {
        logger.info("Looking for all restaurants");
		List<Restaurant> restaurants = null; 
        String restaurantsUri = restaurantServiceUri + "/restaurants";
        logger.info("Looking for " + restaurantsUri);
        Mono<GetRestaurantsResponse> response = webClient
                .get()
                .uri(restaurantsUri)
                .retrieve()
                .bodyToMono(GetRestaurantsResponse.class);
        try {
            GetRestaurantsResponse grr = response.block();
            if (grr != null) {
				restaurants = grr.getRestaurants().stream() 
					.map( r -> getRestaurantResponseToRestaurant(r) ) 
					.collect(Collectors.toList()); 
                logger.info("Restaurants found: " + restaurants.toString());
            } else {
                logger.info("Restaurants not found");
            }
        } catch (WebClientException e) {
            logger.info("Restaurants not found, with exception " + e.getMessage());
        }
		return restaurants; 
    }

}
