package com.aehtiopicus.licpad.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.aehtiopicus.licpad.web.controllers.constants.UrlConstants;
import com.aehtiopicus.licpad.web.controllers.constants.ViewConstants;

@Controller
@RequestMapping(value=UrlConstants.ERROR_MAIN)
public class ErrorController {

	@RequestMapping(value=UrlConstants.ERROR_404)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ModelAndView badRequest(){
		ModelAndView mav = new ModelAndView(ViewConstants.BAD_REQUEST_VIEW);
		return mav;
	}
	
}
