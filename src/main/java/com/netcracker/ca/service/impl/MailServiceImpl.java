package com.netcracker.ca.service.impl;

import java.io.StringWriter;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.netcracker.ca.model.Mail;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	public static String FROM_EMAIL = "collaboration.assistant@gmail.com";

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Override
	public void send(final Mail mail) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
				message.setTo(mail.getTo());
				message.setFrom(mail.getFrom());
				message.setSubject(mail.getSubject());
				message.setSentDate(new Date());
				message.setText(mail.getContent(), true);
			}
		};
		mailSender.send(preparator);
	}
	
	private Mail buildMail(String to, String subject, String content) {
		return new Mail(FROM_EMAIL, to, subject, content);
	}

	@Override
	public void sendProjectEndMail(Project project, User user) {
		Template template = velocityEngine.getTemplate("/WEB-INF/email-templates/project-end.vm");
		VelocityContext context = new VelocityContext();
		context.put("project", project);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		send(buildMail(user.getEmail(), "Project ending soon", writer.toString()));
	}

}
