package com.aehtiopicus.licpad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.aehtiopicus.licpad")
@ImportResource("classpath:/licpad-properties.xml")
public class ApplicationContext extends WebMvcConfigurerAdapter{
	

	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver vr = new UrlBasedViewResolver();
		vr.setViewClass(JstlView.class);
		vr.setPrefix("/WEB-INF/views/");
		vr.setSuffix(".jsp");
		return vr;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSize(1 * 1024 * 1024 * 1024);// 1gb??
		return cmr;
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    
	    ResourceHandlerRegistration _res = 
	    		registry.addResourceHandler("/WEB-INF/views/**");
	        _res.addResourceLocations(
	            "classpath:/webapp/WEB-INF/views/");
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry aRegistry)
    {
        aRegistry.addViewController("/").setViewName("index");
    }

}
