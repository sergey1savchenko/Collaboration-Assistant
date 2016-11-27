package com.netcracker.ca.utils;

public class StorageException extends RuntimeException {
	private static final long serialVersionUID = 4554970639096302883L;

	public StorageException(String message, Throwable e) {
		super(message, e);
	}

	public StorageException(Throwable e) {
		super(e);
	}
	
	public StorageException(String message) {
		super(message);
	}
}
