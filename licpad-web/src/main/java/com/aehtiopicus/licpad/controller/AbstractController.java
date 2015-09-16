package com.aehtiopicus.licpad.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.aehtiopicus.licpad.controller.constants.VistasConstants;
import com.aehtiopicus.licpad.utils.PageNotFoundException;


@ControllerAdvice
public class AbstractController {


	private static final Logger logger = Logger.getLogger(AbstractController.class);


	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class})
	public ModelAndView handleFormException(Exception ex) {
		logger.error(ex.getMessage());
		ex.printStackTrace();
		ModelAndView mv = new ModelAndView(VistasConstants.ERROR_VIEW);
		mv.addObject("error",ex.getMessage());
		return mv;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({PageNotFoundException.class})
	public ModelAndView handleFormException(PageNotFoundException ex) {		
		
		ModelAndView mv = new ModelAndView(VistasConstants.NOT_FOUND);
		mv.addObject("error",ex.getMessage());
		return mv;
	}
			
	
}
