package running.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import running.web.WebConfig;
import javax.servlet.MultipartConfigElement;

public class RunningInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{ "/" };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO: page 203
		registration.setMultipartConfig(new MultipartConfigElement("c:\\tmp\\runner\\uploads", 2097152, 4194304, 0));
	}

		
}
