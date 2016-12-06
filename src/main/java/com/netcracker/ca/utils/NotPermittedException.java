package com.netcracker.ca.utils;

public class NotPermittedException extends RuntimeException {
	private static final long serialVersionUID = 4291390941393462770L;

	public NotPermittedException(String message) {
		super(message);
	}
}
