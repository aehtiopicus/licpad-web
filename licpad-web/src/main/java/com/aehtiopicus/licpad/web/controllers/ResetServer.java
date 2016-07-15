package com.aehtiopicus.licpad.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aehtiopicus.licpad.web.controllers.constants.UrlConstants;
import com.aehtiopicus.licpad.web.dto.RestSingleResponseDto;
import com.aehtiopicus.licpad.web.init.Main;



@Controller
public class ResetServer {

	@RequestMapping(value=UrlConstants.RESET_SERVER, produces= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> stopServer() throws Exception{		
		Main.getInstance().killServerAndReset();
		RestSingleResponseDto rsDto = new RestSingleResponseDto();
		rsDto.setMessage("Server restarting OK");
		return new ResponseEntity<RestSingleResponseDto>(rsDto, HttpStatus.OK);
		
	}
}
