package com.netcracker.ca.service;

import java.util.Map;

public interface TemplateBuilder {
	
	String build(String template, Map<String, Object> model);
	
}
