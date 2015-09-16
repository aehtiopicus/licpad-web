package com.aehtiopicus.licpad.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationContext.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ApplicationContext.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}