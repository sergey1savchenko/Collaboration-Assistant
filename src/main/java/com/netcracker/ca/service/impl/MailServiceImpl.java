package com.netcracker.ca.service.impl;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.netcracker.ca.model.Mail;
import com.netcracker.ca.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	public static String FROM_EMAIL = "collaboration.assistant@gmail.com";

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void send(Mail mail, List<String> to) {
		String[] toArr = new String[to.size()];
		to.toArray(toArr);
		send(mail, toArr);
	}

	@Override
	public void send(Mail mail, String to) {
		send(mail, new String[] { to });
	}
	
	private void send(final Mail mail, final String[] to) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
				message.setTo(to);
				message.setFrom(FROM_EMAIL);
				message.setSubject(mail.getSubject());
				message.setSentDate(new Date());
				message.setText(mail.getContent(), true);
			}
		};
		mailSender.send(preparator);
	}

}
