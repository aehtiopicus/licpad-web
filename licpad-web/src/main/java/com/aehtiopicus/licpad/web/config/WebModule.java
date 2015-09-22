package com.aehtiopicus.licpad.web.config;

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

@EnableWebMvc
@ComponentScan(basePackages={"com.aehtiopicus.licpad"})
@ImportResource("classpath:/resources/licpad-properties.xml")
@Configuration
public class WebModule extends WebMvcConfigurerAdapter
{
	@Override
	public void addViewControllers(ViewControllerRegistry aRegistry)
	{
		super.addViewControllers(aRegistry);
		aRegistry.addViewController("/").setViewName("index");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry aRegistry)
	{
		aRegistry.addResourceHandler("/s/*").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/view/scripts/*");
		aRegistry.addResourceHandler("/c/*").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/view/css/*");
		aRegistry.addResourceHandler("/i/*").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/view/images/*");
		aRegistry.addResourceHandler("/WEB-INF/view/**").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/view/*");
		aRegistry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
	}
	
	@Bean
	public ViewResolver viewResolver() 
	{
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
