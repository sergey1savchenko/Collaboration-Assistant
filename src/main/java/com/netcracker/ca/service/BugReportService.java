package com.netcracker.ca.service;

import com.netcracker.ca.model.BugReport;

public interface BugReportService {

	void report(BugReport bugReport, String mail);
}
