package asw.ordermanager.productservice.rest;

import asw.ordermanager.productservice.domain.*;

import asw.ordermanager.productservice.api.rest.*;

import org.springframework.web.bind.annotation.*; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

import java.util.logging.Logger; 

@RestController
public class ProductsController {

	@Autowired 
	private ProductService productService; 

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	/* Crea un nuovo prodotto. */ 
	@PostMapping("/products")
	public GetProductResponse createProduct(@RequestBody CreateProductRequest request) {
		String name = request.getName();
		String category = request.getCategory();
		int stockLevel = request.getStockLevel();
		double price = request.getPrice();
		logger.info("REST CALL: createProduct " + name + ", " + category + ", " + stockLevel + ", " + price); 
		Product product = productService.createProduct(name, category, stockLevel, price);
		GetProductResponse productResponse = toProductResponse(product);
		return productResponse; 
	}	

	/* Trova il prodotto con name. */ 
	@GetMapping("/products/{name}")
	public GetProductResponse getProduct(@PathVariable String name) {
		logger.info("REST CALL: getProduct " + name); 
		Product product = productService.getProduct(name);
		if (product==null) {
			logger.info("REST CALL: getProduct " + name + ": Product not found"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Product not found"
			);
		}
		GetProductResponse productResponse = toProductResponse(product);
		logger.info("REST CALL: getProduct " + name + ": " + productResponse); 
		return productResponse; 
	}

	/* Trova tutti i prodotti. */ 
	@GetMapping("/products")
	public GetProductsResponse getProducts() {
		Collection<Product> products = null; 
		GetProductsResponse productsResponse = null; 
		logger.info("REST CALL: getProducts"); 
		products = productService.getProducts();
		productsResponse = toProductsResponse(products);
		logger.info("REST CALL: getProducts: " + productsResponse); 
		return productsResponse;
	}
	
	/* Trova tutti i prodotti che hanno i nomi dati. */ 
	@PostMapping("/findproducts/bynames")
	public GetProductsResponse findProductsByNames(@RequestBody GetProductsByNamesRequest request) {
		List<String> names = request.getNames(); 
		Collection<Product> products = null; 
		GetProductsResponse productsResponse = null; 
		logger.info("REST CALL: getProductsByNames " + names); 
		products = productService.getProductsByNames(names);
		productsResponse = toProductsResponse(products);
		logger.info("REST CALL: getProductsByNames " + names + ": " + productsResponse); 
		return productsResponse;
	}

//	/* Trova tutti i prodotti che hanno i nomi dati (versione alternativa, riceve direttamente la lista di nomi). */ 
//	@PostMapping("/findproducts/bynames")
//	public GetProductsResponse findProductsByNameList(@RequestBody List<String> names) {
//		Collection<Product> products = null; 
//		GetProductsResponse productsResponse = null; 
//		logger.info("REST CALL: getProductsByNameList " + names); 
//		products = productService.getProductsByNames(names);
//		productsResponse = toProductsResponse(products);
//		logger.info("REST CALL: getProductsByNameList " + names + ": " + productsResponse); 
//		return productsResponse;
//	}
	
	/* Modifica la quantità di un prodotto. */ 
	@PatchMapping("/products")
	public GetProductResponse updateProductStockLevel(@RequestBody UpdateProductStockLevelRequest request) {
		String name = request.getName();
		int stockLevelVariation = request.getStockLevelVariation();
		logger.info("REST CALL: updateProductStockLevel " + name + ", " + stockLevelVariation); 
		Product product = productService.updateProductStockLevel(name, stockLevelVariation);
		GetProductResponse productResponse = toProductResponse(product);
		logger.info("REST CALL: updateProductStockLevel " + name + ", " + stockLevelVariation + ": " + productResponse); 
		return productResponse; 
	}	

	/* Converte un Product in una GetProductResponse. */ 
	private GetProductResponse toProductResponse(Product product) {
		return new GetProductResponse(
			product.getName(), 
			product.getStockLevel(), 
			product.getPrice()); 
	}

	/* Converte una collezione di Product in una GetProductsResponse. */ 
	private GetProductsResponse toProductsResponse(Collection<Product> products) {
		List<GetProductResponse> productsResponse = 
			products
				.stream()
				.map(product -> toProductResponse(product))
				.collect(Collectors.toList());
		return new GetProductsResponse(productsResponse); 
	}
	
}
