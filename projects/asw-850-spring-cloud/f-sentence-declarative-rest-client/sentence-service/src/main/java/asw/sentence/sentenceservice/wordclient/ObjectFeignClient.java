package asw.sentence.sentenceservice.wordclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("object")
public interface ObjectFeignClient {

	@GetMapping("/")
	public String getWord(); 

}
