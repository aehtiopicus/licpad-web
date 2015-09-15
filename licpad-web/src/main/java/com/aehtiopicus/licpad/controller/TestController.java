package com.aehtiopicus.licpad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class TestController {

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = {"/cow"}, method=RequestMethod.GET)
	public void doSomthing(){
		System.out.println("aaaaa");
	}
}
