package com.netcracker.ca.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;

public interface StorageService {
	
	void store(InputStream is, String path) throws IOException;

	Resource retrieve(String path);
	
	void delete(String path);
	
}
