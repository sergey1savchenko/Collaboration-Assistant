package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Mail;

public interface MailService {
	
	void send(Mail mail, String to);

	void send(Mail mail, List<String> to);

}
