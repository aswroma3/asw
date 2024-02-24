package asw.springboot.web.hello;

import java.util.Map; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public @ResponseBody String hello() {
		return "Hello, world!"; 
	}

	@RequestMapping("/hello/{name}")
	public String hello(Map<String, Object> model, @PathVariable String name) {
		model.put("name", name); 
		return "greeting"; 
	}
	
}
