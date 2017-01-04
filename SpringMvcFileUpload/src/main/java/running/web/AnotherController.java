package running.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AnotherController {

	@RequestMapping("/exampleException")
	public void exampleException() {
		throw new AnotherExampleException();
	}
}
