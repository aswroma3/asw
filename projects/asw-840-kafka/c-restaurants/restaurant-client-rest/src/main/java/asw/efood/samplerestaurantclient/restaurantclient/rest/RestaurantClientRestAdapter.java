package asw.efood.samplerestaurantclient.restaurantclient.rest;

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

    @Value("${asw.efood.restaurantservice.rest.uri.scheme}")
    private String restaurantServiceUriScheme;
    @Value("${asw.efood.restaurantservice.rest.uri.host}")
    private String restaurantServiceUriHost;
    @Value("${asw.efood.restaurantservice.rest.uri.port}")
    private int restaurantServiceUriPort;
    @Value("${asw.efood.restaurantservice.rest.uri.basepath}")
    private String restaurantServiceUriBasePath;

    private WebClient webClient;

    public RestaurantClientRestAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(restaurantServiceUri).build();
    }

    public Long createRestaurant(String name, String location) throws RestaurantServiceException {
        logger.info("Creating restaurant " + name + " " + location);
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
			restaurantId = crr.getRestaurantId(); 
            logger.info("Restaurant created with: " + restaurantId);
			return restaurantId; 
        } catch (WebClientException e) {
            logger.info("Restaurant not created, with exception " + e.getMessage());
			throw new RestaurantServiceException("RestaurantServiceException for createRestaurant(" + name + ", " + location + ")");
        }
    }

    public Restaurant getRestaurant(Long restaurantId) throws RestaurantServiceException {
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
			restaurant = getRestaurantResponseToRestaurant(grr);
            logger.info("Restaurant found: " + restaurant.toString());
			return restaurant; 
        } catch (WebClientException e) {
            logger.info("Restaurant not found, with exception " + e.getMessage());
			throw new RestaurantServiceException("RestaurantServiceException for getRestaurant(" + restaurantId + ")");
        }
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

    public Restaurant getRestaurantByNameAndLocation(String name, String location) throws RestaurantServiceException {
        logger.info("Looking for restaurant with " + name + " and " + location);
        Restaurant restaurant = null;
        String restaurantUri = restaurantServiceUri + "/restaurants/findByNameAndLocation/";
        String restaurantPath = restaurantServiceUriBasePath + "/restaurants/findByNameAndLocation/";
        logger.info("Looking for " + restaurantUri + " with " + name + " and " + location);
        Mono<GetRestaurantResponse> response = webClient
                .get()
//				.uri(restaurantUri + "?name=" + name + "&location="+location)
				.uri(builder -> builder
					.scheme(restaurantServiceUriScheme)
					.host(restaurantServiceUriHost)
					.port(restaurantServiceUriPort)
					.path(restaurantPath)
					.queryParam("name", name)
					.queryParam("location", location)
					.build())
                .retrieve()
                .bodyToMono(GetRestaurantResponse.class);
        try {
            GetRestaurantResponse grr = response.block();
			restaurant = getRestaurantResponseToRestaurant(grr);
            logger.info("Restaurant found: " + restaurant.toString());
        } catch (WebClientException e) {
            logger.info("Restaurant not found, with exception " + e.getMessage());
			throw new RestaurantServiceException("RestaurantServiceException for getRestaurant(" + name + ", " + location + ")");
        }
		return restaurant; 
    }

    public List<Restaurant> getRestaurantsByLocation(String location) {
        logger.info("Looking for restaurants in " + location);
		List<Restaurant> restaurants = null; 
        String restaurantsUri = restaurantServiceUri + "/restaurants/findByLocation/";
        String restaurantPath = restaurantServiceUriBasePath + "/restaurants/findByLocation/";
        logger.info("Looking for " + restaurantsUri  + " with " + location);
        Mono<GetRestaurantsResponse> response = webClient
                .get()
				.uri(builder -> builder
					.scheme(restaurantServiceUriScheme)
					.host(restaurantServiceUriHost)
					.port(restaurantServiceUriPort)
					.path(restaurantPath)
					.queryParam("location", location)
					.build())
                .retrieve()
                .bodyToMono(GetRestaurantsResponse.class);
        try {
            GetRestaurantsResponse grr = response.block();
            if (grr != null) {
				restaurants = grr.getRestaurants().stream() 
					.map( r -> getRestaurantResponseToRestaurant(r) ) 
					.collect(Collectors.toList()); 
                logger.info("Restaurants in " + location + " found: " + restaurants.toString());
            } else {
                logger.info("Restaurants in " + location + " not found");
            }
        } catch (WebClientException e) {
            logger.info("Restaurants in " + location + " not found, with exception " + e.getMessage());
        }
		return restaurants; 
    }

}
