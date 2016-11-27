package com.netcracker.ca.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResource;

import com.netcracker.ca.service.StorageService;
import com.netcracker.ca.utils.StorageException;

@Service
@PropertySource("/WEB-INF/properties/storage.properties")
public class StorageServiceImpl implements StorageService {
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private Environment env;
	
	private static String rootDirectory;
	
	@PostConstruct
	private void init() {
		rootDirectory = env.getRequiredProperty("rootDirectory");
	}

	@Override
	public void store(InputStream is, String path) {
		Path p = Paths.get(context.getRealPath(rootDirectory + path));
		p.getParent().toFile().mkdirs();
		try {
			Files.copy(is, p);
		} catch (IOException e) {
			throw new StorageException("Failed to save file: " + path, e);
		}
	}
	
	@Override
	public Resource retrieve(String path) {
		Resource res = new ServletContextResource(context, rootDirectory + path);
		if(!res.exists()) 
			throw new StorageException("Failed to retrieve file: " + path);
		return res;
	}

	@Override
	public void delete(String path) {
		File f = new File(context.getRealPath(rootDirectory + path));
		if(!f.delete())
			throw new StorageException("Failed to delete file: " + path);
	}

}