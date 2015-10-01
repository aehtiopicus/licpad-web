package com.aehtiopicus.licpad.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aehtiopicus.licpad.web.controllers.constants.UrlConstants;
import com.aehtiopicus.licpad.web.controllers.constants.ViewConstants;
import com.aehtiopicus.licpad.web.init.Main;

@Controller
public class ResetServer {

	@RequestMapping(value=UrlConstants.RESET_SERVER)
	public ModelAndView stopServer() throws Exception{		
		Main.getInstance().killServerAndReset();
		return new ModelAndView(ViewConstants.RESET_VIEW);
	}
}
