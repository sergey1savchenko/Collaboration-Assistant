package com.netcracker.ca.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.netcracker.ca.utils.RepositoryException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	private Logger logger = LogManager.getLogger("Error");
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RepositoryException.class)
	public String handle(RepositoryException e) {
		logger.error("Internal exception", e);
		return "fail";
	}
}
