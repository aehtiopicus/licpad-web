package com.aehtiopicus.licpad.web.controllers;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aehtiopicus.licpad.core.dao.Persistencia;
import com.aehtiopicus.licpad.core.domain.Cursos;

@Controller
public class Home {

	@Autowired
	private Persistencia p;
	
	private static final Logger log = LogManager.getLogger(Home.class);
	@RequestMapping("/")
	public ModelAndView home()
	{		
		log.error("home");
		Cursos c = p.saveCurso();
		p.savePersona(c);
		return new ModelAndView("index");
		
		
	}
			
	
}
