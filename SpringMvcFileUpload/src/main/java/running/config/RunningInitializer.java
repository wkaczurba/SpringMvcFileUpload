package running.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import running.web.WebConfig;
import javax.servlet.MultipartConfigElement;

public class RunningInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO: Add applicationContext config whenever it is needed.
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{ "/" };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// page 203 for a reference.
		registration.setMultipartConfig(new MultipartConfigElement("c:\\tmp\\runner\\uploads", 2097152, 4194304, 0));
	}

		
}
