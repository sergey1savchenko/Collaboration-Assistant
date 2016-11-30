package com.netcracker.ca.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.BugReport;
import com.netcracker.ca.model.UserAuth;
import com.netcracker.ca.service.BugReportService;

@RestController
public class BugReportController extends BaseApiController {
	
	@Autowired
	private BugReportService bugReportService;

	@PostMapping("user/api/report-bug")
	public void reportBug(@RequestBody BugReport bugReport, @AuthenticationPrincipal UserAuth userAuth) {
		bugReportService.report(bugReport, userAuth.getUsername());
	}
	
}
