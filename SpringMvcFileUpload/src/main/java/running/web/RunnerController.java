package running.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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
	public String fileuploadformProcessing(@Valid FileUploadForm fileUploadForm, Errors errors) throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			System.out.println("Errors...");
			return "fileuploadform";
		}
		MultipartFile file = fileUploadForm.getPicture();
		
		String originalFileNameOnly = Paths.get(
					fileUploadForm.getPicture().getOriginalFilename())
						.getFileName()
						.toString();
		File destination = new File("c:\\tmp\\runner\\saved\\" + originalFileNameOnly);
		
		// TODO: Try doing validation here...
		System.out.println("MultipartFile.getName: " + file.getName());
		System.out.println("MultipartFile.getOriginalName: " + file.getOriginalFilename());
		System.out.println("FIlesize: " + fileUploadForm.getPicture().getSize());
		System.out.println("originalFileNameOnly:" + originalFileNameOnly);
		fileUploadForm.getPicture().transferTo(destination);
		return "redirect:/processed/" + originalFileNameOnly;
	}
	
	// {filename} truncates dots, so filextension is gone.
	// {filename:.+} does not truncate dots.
	@RequestMapping(value="/processed/{filename:.+}")
	public String fileuploadformProcessed(@PathVariable String filename, Model model) {
		System.out.println("In: /processed/" + filename);
		System.out.println("filename: " + filename);
		model.addAttribute("filename", filename);
		return "processed";
	}

	// Lets handle only IDs with 0...2:
	@RequestMapping(value="/lookup/{id}")
	public String lookup(@PathVariable int id, Model model) {
		if (id < 2) {
			model.addAttribute("id", id);
			return "lookup";
		} else {
			throw new LookupIdNotFoundException();
		}
	}
	// TODO: Process form + Validate the form.
}
