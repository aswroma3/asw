package asw.efood.samplerestaurantclient.domain;

import java.util.*; 

public class Restaurant {

	private Long id; 
	private String name; 
	private String location; 
	
	private List<MenuItem> menuItems;

	public Restaurant() {
	}

	public Restaurant(Long id, String name, String location) {
		this(); 
		this.id = id; 
		this.name = name; 
		this.location = location; 
	}

	public Restaurant(Long id, String name, String location, List<MenuItem> menuItems) {
		this(); 
		this.id = id; 
		this.name = name; 
		this.location = location; 
		this.menuItems = menuItems;
	}
	
	public Long getId() {
		return id; 
	}
	
	public void setId(Long id) {
		this.id = id; 
	}

	public String getName() {
		return name; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}

	public String getLocation() {
		return location; 
	}
	
	public void setLocation(String location) {
		this.location = location; 
	}

	public List<MenuItem> getMenuItems() {
		return menuItems; 
	}
	
	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems; 
	}
	
	public String toString() {
		return "Restaurant(" + 
			"id=" + id + 
			", name=" + name + 
			", location=" + location + 
			(menuItems!=null ? ", menuItems=" + menuItems : "") + 
			")"; 
	}
	
}
