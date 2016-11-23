package com.netcracker.ca.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.netcracker.ca.utils.RepositoryException;

@Aspect
@Component
public class RepositoryExceptionWrapper {

	@AfterThrowing(pointcut = "within(com.netcracker.ca.dao..*)", throwing = "ex")
	public void wrapException(DataAccessException ex) {
		throw new RepositoryException("Repository operation failed", ex);
	}

}
