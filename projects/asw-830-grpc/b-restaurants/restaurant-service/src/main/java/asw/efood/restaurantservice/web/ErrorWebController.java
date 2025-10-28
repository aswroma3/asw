package asw.efood.restaurantservice.web;

import asw.efood.restaurantservice.domain.*; 

import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.servlet.ModelAndView; 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.RequestDispatcher;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping
public class ErrorWebController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("error-page");
		
		Object status = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object errorMessage = httpRequest.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		Object requestUri = httpRequest.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
		String method = httpRequest.getMethod();
		String statusString = ""; 
		
        errorPage.addObject("request", method + " " + requestUri.toString());
        errorPage.addObject("errorMessage", errorMessage);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				statusString = "HttpStatus.NOT_FOUND (404)";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				statusString = "HttpStatus.INTERNAL_SERVER_ERROR (500)";
			} else {
				statusString = String.valueOf(status); 
			}
			errorPage.addObject("status", statusString);
		}

        return errorPage;
    }
    
}

