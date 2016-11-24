package com.netcracker.ca.utils;

public class RepositoryException extends RuntimeException {
	private static final long serialVersionUID = -7524621893275079580L;

	public RepositoryException(String message, Throwable e) {
		super(message, e);
	}

	public RepositoryException(Throwable e) {
		super(e);
	}	

}
