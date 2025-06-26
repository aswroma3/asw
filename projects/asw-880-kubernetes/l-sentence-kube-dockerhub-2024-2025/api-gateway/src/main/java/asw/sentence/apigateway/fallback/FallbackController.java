package asw.sentence.apigateway.fallback;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FallbackController {

	@GetMapping("/fallback/sentence")
	public String getSentenceFallback() {
		return "THIS IS THE APIGATEWAY FALLBACK SENTENCE"; 
	}
	
	@GetMapping("/fallback/subject")
	public String getSubjectFallback() {
		return "APIGATEWAY FALLBACK SUBJECT"; 
	}

	@GetMapping("/fallback/verb")
	public String getVerbFallback() {
		return "APIGATEWAY FALLBACK VERB"; 
	}

	@GetMapping("/fallback/object")
	public String getObjectFallback() {
		return "APIGATEWAY FALLBACK OBJECT"; 
	}

}
