package asw.efood.samplerestaurantclient.domain;

public class MenuItem {

	private String id;
	private String name;
	private double price;

	public MenuItem() {
	}

	public MenuItem(String id, String name, double price) {
		this(); 
		this.id = id; 
		this.name = name; 
		this.price = price; 
	}
	
	public String getId() {
		return id; 
	}
	
	public void setId(String id) {
		this.id = id; 
	}

	public String getName() {
		return name; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}

	public double getPrice() {
		return price; 
	}
	
	public void setPrice(double price) {
		this.price = price; 
	}
	
	public String toString() {
		return "MenuItem(" + 
			"id=" + id + 
			", name=" + name + 
			", price=" + price + 
			")"; 
	}

}
