package asw.sentence.sentenceservice.wordclient;

import asw.sentence.sentenceservice.domain.WordClientPort;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import feign.FeignException; 

@Service 
public class ObjectClientAdapter implements WordClientPort {

	@Autowired 
	private ObjectFeignClient feignClient;
	
	public String getWord() {
		String word = null; 
		try {
			word = feignClient.getWord(); 
		} catch (FeignException e) {
			/* eccezione remota */ 
			word = "***"; 
		}
		return word; 
	}
	
}
