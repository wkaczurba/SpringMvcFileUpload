package running.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import org.springframework.validation.Errors;

@Controller
public class RunnerController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		System.out.println("/");
		return "main";
	}
	
	@RequestMapping(value="/fileuploadform", method=RequestMethod.GET)
	public String fileuploadform(Model model) {
		model.addAttribute("fileUploadForm", new FileUploadForm());
		return "fileuploadform";
	}
	
	@RequestMapping(value="/fileuploadform", method=RequestMethod.POST)
	public String fileuploadformProcessing(@Valid FileUploadForm fileUploadForm, Errors errors) {
		if (errors.hasErrors()) {
			System.out.println("Errors...");
			return "fileuploadform";
		}
		// TODO: Try doing validation here...
		System.out.println("FIlesize: " + fileUploadForm.getPicture().getSize());
		return "redirect:/processed";
	}
	
	@RequestMapping(value="/processed")
	public String fileuploadformProcessed() {
		return "processed";
	}

	// TODO: Process form + Validate the form.
}
