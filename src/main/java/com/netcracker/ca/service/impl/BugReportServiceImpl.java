package com.netcracker.ca.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.netcracker.ca.model.BugReport;
import com.netcracker.ca.model.Mail;
import com.netcracker.ca.service.BugReportService;
import com.netcracker.ca.service.MailService;
import com.netcracker.ca.service.TemplateBuilder;

@Service
@PropertySource("/WEB-INF/properties/smtp.properties")
public class BugReportServiceImpl implements BugReportService {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TemplateBuilder builder;

	@Override
	public void report(BugReport bugReport, String email) {
		Map<String, Object> model = new HashMap<>();
		model.put("report", bugReport);
		model.put("email", email);
		Mail mail = new Mail("Collaboration Assistant - Bug Report", builder.build("bug-report.ftl", model));
		
		mailService.send(mail, env.getRequiredProperty("smtp.host"));
	}

}
