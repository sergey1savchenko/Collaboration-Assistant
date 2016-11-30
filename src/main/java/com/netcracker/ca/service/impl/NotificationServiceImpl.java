package com.netcracker.ca.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Mail;
import com.netcracker.ca.model.Meeting;
import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.MailService;
import com.netcracker.ca.service.NotificationService;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.service.TemplateBuilder;
import com.netcracker.ca.service.UserService;

public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TemplateBuilder builder;
	
	@Autowired
	private UserService userService;

	@Override
	public void onProjectCreation(Project project) {
		Map<String, Object> model = new HashMap<>();
		model.put("project", project);
		Mail mail = build("Collaboration Assistant - Project created", "project-created.ftl", model);
		
		List<String> emails = new ArrayList<>();
		for(User admin: userService.getByRole("ADMIN"))
			emails.add(admin.getEmail());
		for(User hr: userService.getByRole("HR"))
			emails.add(hr.getEmail());
		for(User user: userService.getAssociatedWithProject(project.getId()))
			emails.add(user.getEmail());
		
		mailService.send(mail, emails);
	}

	@Override
	public void onStudentAddedToProject(Student student, Project project, Team team) {
		Map<String, Object> model = new HashMap<>();
		model.put("project", project);
		model.put("team", team);
		Mail mail = build("Collaboration Assistant - Project invitation", "project-participation.ftl", model);
		
		mailService.send(mail, student.getEmail());
	}

	@Override
	public void onCuratorAddedToProject(User curator, Project project, Team team) {
		Map<String, Object> model = new HashMap<>();
		model.put("project", project);
		model.put("team", team);
		Mail mail = build("Collaboration Assistant - Project invitation", "project-curatorship.ftl", model);
		
		mailService.send(mail, curator.getEmail());
	}

	@Override
	public void onMeetingCreated(Meeting meeting) {
		Map<String, Object> model = new HashMap<>();
		model.put("meeting", meeting);
		Mail mail = build("Collaboration Assistant - Meeting created", "meeting-created.ftl", model);
		
		/*List<String> emails = new ArrayList<>();
		for(User user: userService.getAssociatedWithTeam(teamService.getByMeeting(meeting.getId()).getId()))
			emails.add(user.getEmail());
		
		mailService.send(mail, emails);*/
	}

	@Override
	public void onMeetingEdited(Meeting meeting) {
		Map<String, Object> model = new HashMap<>();
		model.put("meeting", meeting);
		Mail mail = build("Collaboration Assistant - Meetind rescheduled", "meeting-edited.ftl", model);
		
		List<String> emails = new ArrayList<>();
		/*for(User user: userService.getAssociatedWithTeam(teamService.getByMeeting(meeting.getId()).getId()))
			emails.add(user.getEmail());
		
		mailService.send(mail, emails);*/
	}

	@Override
	public void onMeetingDeleted(Meeting meeting) {
		Map<String, Object> model = new HashMap<>();
		model.put("meeting", meeting);
		Mail mail = build("Collaboration Assistant - Meeting canceled", "meeting-deleted.ftl", model);
		
		List<String> emails = new ArrayList<>();
		/*for(User user: userService.getAssociatedWithTeam(teamService.getByMeeting(meeting.getId()).getId()))
			emails.add(user.getEmail());
		
		mailService.send(mail, emails);*/
	}
	
	@Override
	public void onAttachmentAdded(Attachment attachment) {
		Map<String, Object> model = new HashMap<>();
		model.put("attachment", attachment);
		Mail mail = build("Collaboration Assistant - File attachment added", "attachment-added.ftl", model);
		
		List<String> emails = new ArrayList<>();
		/*if(attachment.getTeam() == null) {
			for(User user: userService.getAssociatedWithProject(attachment.getProject().getId()))
				emails.add(user.getEmail());
		}
		else {
			for(User user: userService.getAssociatedWithTeam(attachment.getTeam().getId()))
				emails.add(user.getEmail());
		}*/
		
		mailService.send(mail, emails);
	}

	@Override
	public void onProjectStatusChanged(Participation part) {
		Map<String, Object> model = new HashMap<>();
		model.put("participation", part);
		Mail mail = build("Collaboration Assistant - Project status changed", "project-status-changed.ftl", model);
		
		//mailService.send(mail, part.getStudent().getEmail());
	}
	
	private Mail build(String subject, String template, Map<String, Object> model) {
		return new Mail(subject, builder.build(template, model));
	}
	
}
