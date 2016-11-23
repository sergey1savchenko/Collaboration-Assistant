package com.netcracker.ca.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryWriteLogger {
	
	private static final Logger logger = LogManager.getLogger("Repository");
			
	@AfterReturning("execution(* com.netcracker.ca.dao.Dao+.add(Object)) && args(entity)")
	private void onRepositoryAdd(JoinPoint joinPoint, Object entity) {
		logger.debug(String.format("%s added %s", joinPoint.getTarget().getClass().getName(), entity)); 
	}
	
	@AfterReturning("execution(* com.netcracker.ca.dao.Dao+.update(Object)) && args(entity)")
	private void onRepositoryUpdate(JoinPoint joinPoint, Object entity) {
		logger.debug(String.format("%s updated %s", joinPoint.getTarget().getClass().getName(), entity)); 
	}
	
	@AfterReturning("execution(* com.netcracker.ca.dao.Dao+.delete(Object)) && args(id)")
	private void onRepositoryDelete(JoinPoint joinPoint, Object id) {
		logger.debug(String.format("%s deleted with id %s", joinPoint.getTarget().getClass().getName(), id)); 
	}
	
}
