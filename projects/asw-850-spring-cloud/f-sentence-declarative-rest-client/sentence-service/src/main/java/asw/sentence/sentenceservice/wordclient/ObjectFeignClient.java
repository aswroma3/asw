package asw.sentence.sentenceservice.wordclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${asw.sentence.sentenceservice.object.feignName}")
public interface ObjectFeignClient {

	@GetMapping("/")
	public String getWord(); 

}
