package asw.ordermanager.ordervalidationservice.productserviceclient;

import asw.ordermanager.ordervalidationservice.domain.*; 

import asw.ordermanager.productservice.api.rest.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.BodyInserters; 

import java.util.*; 
import java.util.stream.*; 

import java.util.logging.Logger; 

@Service 
public class ProductServiceRestClientAdapter implements ProductServiceClientPort {

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;
	
    private final Logger logger = Logger.getLogger(this.getClass().toString());
	
	public Product getProduct(String name) {
		GetProductResponse pr = null; 
        Mono<GetProductResponse> response = loadBalancedWebClient
                .get()
				.uri("http://productservice/products/{name}", name)
                .retrieve()
                .bodyToMono(GetProductResponse.class);
        try {
            pr = response.block();
        } catch (WebClientException e) {
			logger.info("GETPRODUCT " + name + ": " + e.getMessage());
        }
		return toProduct(pr); 
	}		

	public List<Product> getProductsByNames(List<String> names) {
		GetProductsByNamesRequest request = new GetProductsByNamesRequest(names); 
		GetProductsResponse pr = null; 
        Mono<GetProductsResponse> response = loadBalancedWebClient
                .post()
				.uri("http://productservice/findproducts/bynames")
				.body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(GetProductsResponse.class);
        try {
            pr = response.block();
        } catch (WebClientException e) {
			logger.info("GETPRODUCTSBYNAME " + names + ": " + e.getMessage());
        }
		return toProducts(pr); 
	}		

	private Product toProduct(GetProductResponse pr) {
		if (pr==null) {
			return null; 
		}
		return new Product(
			pr.getName(), 
			pr.getStockLevel(), 
			pr.getPrice());
	}

	private List<Product> toProducts(GetProductsResponse pr) {
		List<Product> products = 
			pr.getProducts()
				.stream()
				.map(product -> toProduct(product))
				.collect(Collectors.toList());
		return products; 
	}
	
}
