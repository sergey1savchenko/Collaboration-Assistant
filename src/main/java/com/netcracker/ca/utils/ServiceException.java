package com.netcracker.ca.utils;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -59030415251903926L;

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}

	public ServiceException(Throwable e) {
		super(e);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException() {
		super();
	}

}
