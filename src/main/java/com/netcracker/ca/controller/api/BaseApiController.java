package com.netcracker.ca.controller.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.netcracker.ca.utils.RepositoryException;
import com.netcracker.ca.utils.ServiceException;

public class BaseApiController {

private Logger logger = LogManager.getLogger("Error.Repository");
	
	@ExceptionHandler(RepositoryException.class)
	public ResponseEntity<String> handleRepositoryException(RepositoryException e) {
		logger.error("Internal exception", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error. Check back later");
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<String> handleServiceException(ServiceException e) {
		logger.error("Business logic exception", e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	
}
