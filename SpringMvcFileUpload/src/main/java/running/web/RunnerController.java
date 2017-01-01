package running.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RunnerController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		System.out.println("/");
		return "main";
	}
}
