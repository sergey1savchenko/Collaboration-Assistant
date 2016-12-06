package com.netcracker.ca.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.netcracker.ca.utils.NotPermittedException;
import com.netcracker.ca.utils.RepositoryException;

public class BaseController {

	private Logger logger = LogManager.getLogger("Error.Repository");
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RepositoryException.class)
	public String handleRepositoryException(RepositoryException e) {
		logger.error("Internal exception", e);
		return "errorPage";
	}
	

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(NotPermittedException.class)
	public String handleRepositoryException(NotPermittedException e) {
		return "redirect:login";
	}
	
}
