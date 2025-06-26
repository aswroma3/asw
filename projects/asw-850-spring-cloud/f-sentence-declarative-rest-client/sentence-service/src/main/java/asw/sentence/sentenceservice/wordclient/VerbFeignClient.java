package asw.sentence.sentenceservice.wordclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${asw.sentence.sentenceservice.verb.feignName}")
public interface VerbFeignClient {

	@GetMapping("/")
	public String getWord(); 

}
