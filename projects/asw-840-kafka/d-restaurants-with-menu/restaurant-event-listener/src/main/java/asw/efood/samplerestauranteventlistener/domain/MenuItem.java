package asw.efood.samplerestauranteventlistener.domain;

import lombok.*; 

@Data 
@NoArgsConstructor @AllArgsConstructor
public class MenuItem {

	private String id;
	private String name;
	private double price;
	
}
