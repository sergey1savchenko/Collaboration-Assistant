package com.netcracker.ca.service;

import com.netcracker.ca.model.Mail;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.User;

public interface MailService {

	void send(Mail mail);
	
	void sendProjectEndMail(Project project, User admin);
}
