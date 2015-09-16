package com.aehtiopicus.licpad.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.aehtiopicus.licpad.controller.constants.VistasConstants;


@Controller
public class ErrorController extends AbstractController{


	@RequestMapping(value="/errors/404", method=RequestMethod.GET)
	public ModelAndView pageNotFound(HttpServletResponse response) {
		ModelAndView mav =new ModelAndView("redirect:/mvc/displayError");
		mav.addObject("code", HttpStatus.NOT_FOUND.value());
		return mav;
	}
	
	@RequestMapping(value={"/errors/401"}, method=RequestMethod.GET)
	public ModelAndView unauthorized(HttpServletResponse response) {
		ModelAndView mav =new ModelAndView("redirect:/mvc/displayError");
		mav.addObject("code", HttpStatus.UNAUTHORIZED.value());
		return mav;
	}
	

	@RequestMapping(value={"/errors/403"}, method=RequestMethod.GET)
	public ModelAndView forbidden(HttpServletResponse response)  {
		ModelAndView mav =new ModelAndView("redirect:/mvc/displayError");
		mav.addObject("code", HttpStatus.FORBIDDEN.value());
		return mav;
	}
	
	@RequestMapping(value="/mvc/displayError", method=RequestMethod.GET)
	public ModelAndView displayError(@RequestParam("code")int code) {
		ModelAndView mav = new ModelAndView(VistasConstants.MAIN);
		switch(HttpStatus.valueOf(code)){
		case NOT_FOUND:			
			mav.setViewName(VistasConstants.NOT_FOUND);
			break;
		case UNAUTHORIZED:
			mav.setViewName(VistasConstants.UNAUTHORIZED);
			break;
		case FORBIDDEN:
			mav.setViewName(VistasConstants.FORBIDDEN);
			break;
		default:
			break;
		
		}		
	    return mav;
	}
	
}
