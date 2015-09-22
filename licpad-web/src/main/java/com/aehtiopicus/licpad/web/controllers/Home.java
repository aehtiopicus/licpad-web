package com.aehtiopicus.licpad.web.controllers;


import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {

	
	private static final Logger log = LoggerFactory.getLogger(Home.class);
	@RequestMapping("/")
	public ModelAndView home()
	{
		LogManager.getLogger(Home.class).info("aaa");
		log.error("home");
		return new ModelAndView("index");
	}
	

	@RequestMapping("/401")
	public ModelAndView home2()
	{
		log.debug("4");
		return new ModelAndView("errors/401");
	}
	
}
