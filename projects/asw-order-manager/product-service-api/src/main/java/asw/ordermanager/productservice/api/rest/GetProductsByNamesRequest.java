package asw.ordermanager.productservice.api.rest;

import java.util.*; 

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class GetProductsByNamesRequest {

	private List<String> names; 

}

