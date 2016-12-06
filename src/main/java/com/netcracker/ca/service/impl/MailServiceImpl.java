package com.netcracker.ca.service.impl;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.netcracker.ca.model.Mail;
import com.netcracker.ca.service.MailService;

@Service
@PropertySource("/WEB-INF/properties/smtp.properties")
public class MailServiceImpl implements MailService {
	
	private Environment env;

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
	
	@Async
	private void send(final Mail mail, final String[] to) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
				message.setTo(to);
				message.setFrom(env.getRequiredProperty("smtp.host"));
				message.setSubject(mail.getSubject());
				message.setSentDate(new Date());
				message.setText(mail.getContent(), true);
			}
		};
		//Temporarily switched off
		//mailSender.send(preparator);
	}

}
