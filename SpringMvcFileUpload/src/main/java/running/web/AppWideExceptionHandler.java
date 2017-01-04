package running.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppWideExceptionHandler {
	@ExceptionHandler(LookupIdNotFoundException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Not found; Response from AppWideException")
	public void lookupIdNotFound() {
		//return "error/lookUpIdNotFound";
	}
	
	@ExceptionHandler(AnotherExampleException.class)
	public ModelAndView anotherExample() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("customerror");
		modelAndView.addObject("errorType", "AppWideExceptionHandler handled AnotherExampleException.class");
		return modelAndView;
	}

}
