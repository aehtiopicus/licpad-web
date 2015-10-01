package com.aehtiopicus.licpad.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@ComponentScan(basePackages={"com.aehtiopicus.licpad"})
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
		aRegistry.addResourceHandler("/js/**").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/js/");
		aRegistry.addResourceHandler("/css/**").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/css/");
		aRegistry.addResourceHandler("/img/**").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/images/");
		aRegistry.addResourceHandler("/WEB-INF/view/*").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/view/*");
		aRegistry.addResourceHandler("/lw/favicon.ico").addResourceLocations("classpath:/META-INF/webapp/WEB-INF/images/favicon.ico"); 
		aRegistry.addResourceHandler("/resources/*").addResourceLocations("classpath:/resources/");
	}
	
	@Bean
	public ViewResolver viewResolver() 
	{
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	

}
