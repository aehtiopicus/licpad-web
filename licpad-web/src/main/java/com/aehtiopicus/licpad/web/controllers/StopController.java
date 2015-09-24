package com.aehtiopicus.licpad.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aehtiopicus.licpad.web.controllers.constants.UrlConstants;
import com.aehtiopicus.licpad.web.controllers.constants.ViewConstants;
import com.aehtiopicus.licpad.web.init.Main;

@Controller
public class StopController {
	
	@RequestMapping(value=UrlConstants.STOP_SERVER)
	public ModelAndView stopServer() throws Exception{
		Main.getInstance().killServer();
		return new ModelAndView(ViewConstants.STOP_VIEW);
	}
}
