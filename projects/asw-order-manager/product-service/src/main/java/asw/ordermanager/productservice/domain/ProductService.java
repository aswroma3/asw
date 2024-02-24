package asw.ordermanager.productservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

 	public Product createProduct(String name, String category, int stockLevel, double price) {
		Product product = new Product(name, category, stockLevel, price); 
		product = productRepository.save(product);
		return product;
	}

 	public Product getProduct(String name) {
		Product product = productRepository.findById(name).orElse(null);
		return product;
	}

	public Collection<Product> getProducts() {
		Collection<Product> products = productRepository.findAll();
		return products;
	}

	public Collection<Product> getProductsByCategory(String category) {
		Collection<Product> products = productRepository.findByCategory(category);
		return products;
	}	

	public Collection<Product> getProductsByNames(List<String> names) {
		Collection<Product> products = productRepository.findByNameIn(names);
		return products;
	}	

 	public Product updateProductStockLevel(String name, int stockLevelVariation) {
		Product product = getProduct(name); 
		product.setStockLevel(product.getStockLevel() + stockLevelVariation);
		product = productRepository.save(product);
		return product;
	}

}
