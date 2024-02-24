package asw.sentence.sentenceservice.wordclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("subject")
public interface SubjectFeignClient {

	@GetMapping("/")
	public String getWord(); 

}
