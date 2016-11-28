package com.netcracker.ca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.ca.model.BugReport;
import com.netcracker.ca.service.BugReportService;
import com.netcracker.ca.service.MailService;

@Service
public class BugReportServiceImpl implements BugReportService {
	
	@Autowired
	private MailService mailService;

	@Override
	public void report(BugReport bugReport, String mail) {
		//TODO
	}

}
