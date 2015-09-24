package com.aehtiopicus.licpad.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aehtiopicus.licpad.web.controllers.constants.UrlConstants;

@ControllerAdvice
public class MainControllerAdvice {

	 	@ExceptionHandler(NoHandlerFoundException.class)
	    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
	            ModelAndView mav = new ModelAndView("redirect:"+UrlConstants.ERROR_MAIN+UrlConstants.ERROR_404);
	            mav.addObject("exception", e);  
	            return mav;
	    }
}
