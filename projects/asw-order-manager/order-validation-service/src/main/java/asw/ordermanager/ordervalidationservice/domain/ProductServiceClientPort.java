package asw.ordermanager.ordervalidationservice.domain;

import java.util.*; 

public interface ProductServiceClientPort {

	public Product getProduct(String name); 

//	public List<Product> getProducts(); 

	public List<Product> getProductsByNames(List<String> names); 

}
