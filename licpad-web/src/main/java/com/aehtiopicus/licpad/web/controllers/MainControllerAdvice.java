package com.aehtiopicus.licpad.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aehtiopicus.licpad.core.exceptions.LicpadServerException;
import com.aehtiopicus.licpad.web.dto.ErrorDto;

@ControllerAdvice
public class MainControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(MainControllerAdvice.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public @ResponseBody ErrorDto handleError404(HttpServletRequest request, Exception e) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		return errorDto;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ LicpadServerException.class })
	public @ResponseBody ErrorDto handleFormCensException(LicpadServerException ex) {
		logger.error(ex.getMessage());
		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatusCode(HttpStatus.BAD_REQUEST);
		errorDto.setMessage(ex.getMessage());
		errorDto.setErrors(null);
		return errorDto;

	}
}
