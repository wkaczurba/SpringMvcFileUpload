package running.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import running.Runner;

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
		if (id < 3) {
			model.addAttribute("id", id);
			return "lookup";
		} else {
			throw new LookupIdNotFoundException();
		}
	}
	// TODO: Process form + Validate the form.
	
	@RequestMapping(value="/redirectExASource")
	public String redirectExampleASource(Model model) {
		// TO WORK ON...
		System.out.println("redirectExASource");
		model.addAttribute("value", "3");
		return "redirect:/redirectExADestination/{value}"; // it should call "redirectDestination/3"
	}
	
	@RequestMapping(value="/redirectExADestination/{value}")
	public String redirectExampleADestination(@PathVariable int value, Model model) {
		System.out.println("redirectExADestination; value:" + value);		

		return "redirectedExA";
	}
	
	@RequestMapping(value="/redirectExBSource")
	public String redirectExBSource(RedirectAttributes model) {
		Runner runner = new Runner("tomaz", "Tomasz", "Mazowiecki");
		
		model.addFlashAttribute("flashAttrRunner", runner);
		System.out.println("redirectExBSource");
		return "redirect:/redirectExBDestination";
	}
	
	@RequestMapping(value="/redirectExBDestination")
	public String redirectExBDestination(Model model) {
		System.out.println("redirectExBDestination");
		if (model.containsAttribute("flashAttrRunner")) {
			System.out.println("found flashAttrRunner!; details: " + model.asMap().get("flashAttrRunner").toString()); 
		} else {
			System.out.println("no flashAttrRunner found.");
		}
		return "redirectedExB";
		
	}
}
