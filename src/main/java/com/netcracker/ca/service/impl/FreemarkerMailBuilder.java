package com.netcracker.ca.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.netcracker.ca.service.TemplateBuilder;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Component
public class FreemarkerMailBuilder implements TemplateBuilder {

	@Autowired
	Configuration config;

	@Override
	public String build( String template, Map<String, Object> model) {
		try {
			return  FreeMarkerTemplateUtils.processTemplateIntoString( 
			        config.getTemplate(template), model);
		} catch (IOException | TemplateException e) {
			//TODO
			e.printStackTrace();
		}
		return null;
	}

}
