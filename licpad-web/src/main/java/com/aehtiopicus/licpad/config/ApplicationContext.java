package com.aehtiopicus.licpad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.aehtiopicus.licpad"})
@ImportResource("classpath:/resources/licpad-properties.xml")
public class ApplicationContext extends WebMvcConfigurerAdapter{
	

	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver vr = new UrlBasedViewResolver();
		vr.setViewClass(JstlView.class);
		vr.setPrefix("WEB-INF/views/");
		vr.setSuffix(".jsp");
		return vr;
	}

	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry aRegistry) {
		aRegistry.addResourceHandler("/s/*").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/views/scripts/*");
		aRegistry.addResourceHandler("/c/*").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/views/css/*");
		aRegistry.addResourceHandler("/i/*").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/views/images/*");
		aRegistry.addResourceHandler("/WEB-INF/views/*").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/views/*");		
	    aRegistry.addResourceHandler("/resources/*").addResourceLocations("classpath:/resources/");
	    
	 
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry aRegistry)
    {
        aRegistry.addViewController("/").setViewName("index");
    }

}
