package running.web;

import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {
	
	@NotNull
	private MultipartFile picture;

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

}
