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
public class StopController {
	
	@RequestMapping(value=UrlConstants.STOP_SERVER, produces= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> stopServer() throws Exception{
		Main.getInstance().killServer();
		RestSingleResponseDto rsDto = new RestSingleResponseDto();
		rsDto.setMessage("Server stopped OK");
		return new ResponseEntity<RestSingleResponseDto>(rsDto, HttpStatus.OK);
	}
	
	
}
