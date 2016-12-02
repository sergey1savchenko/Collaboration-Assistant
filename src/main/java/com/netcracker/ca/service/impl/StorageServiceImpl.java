package com.netcracker.ca.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	private Environment env;

	@Autowired
	private ServletContext context;

	@Override
	public void store(InputStream is, String path) throws IOException {
		Path p = Paths.get(context.getRealPath(env.getRequiredProperty("rootDirectory") + path));
		p.getParent().toFile().mkdirs();
		Files.copy(is, p);
	}

	@Override
	public Resource retrieve(String path) {
		Resource res = new ServletContextResource(context, env.getRequiredProperty("rootDirectory") + path);
		if (!res.exists())
			throw new StorageException("No resource found by specified path: " + path);
		return res;
	}

	@Override
	public void delete(String path) {
		File f = new File(context.getRealPath(env.getRequiredProperty("rootDirectory") + path));
		if (!f.delete())
			throw new StorageException("Could not delete file: " + path);
	}

}
