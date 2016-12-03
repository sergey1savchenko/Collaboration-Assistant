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
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.MailService;
import com.netcracker.ca.service.NotificationService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.StudentService;
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
	
	@Autowired
	private CuratorService curatorService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private ProjectService projectService;

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
		for(User curator: curatorService.getByProject(project.getId()))
			emails.add(curator.getEmail());
		for(Student student: studentService.getByProject(project.getId()))
			emails.add(student.getEmail());
		
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
		
		List<String> emails = new ArrayList<>();
		for(User curator: curatorService.getByMeeting(meeting.getId()))
			emails.add(curator.getEmail());
		for(Student student: studentService.getByMeeting(meeting.getId()))
			emails.add(student.getEmail());
		
		mailService.send(mail, emails);
	}

	@Override
	public void onMeetingEdited(Meeting meeting) {
		Map<String, Object> model = new HashMap<>();
		model.put("meeting", meeting);
		Mail mail = build("Collaboration Assistant - Meetind rescheduled", "meeting-edited.ftl", model);
		
		List<String> emails = new ArrayList<>();
		for(User curator: curatorService.getByMeeting(meeting.getId()))
			emails.add(curator.getEmail());
		for(Student student: studentService.getByMeeting(meeting.getId()))
			emails.add(student.getEmail());
		
		mailService.send(mail, emails);
	}

	@Override
	public void onMeetingDeleted(Meeting meeting) {
		Map<String, Object> model = new HashMap<>();
		model.put("meeting", meeting);
		Mail mail = build("Collaboration Assistant - Meeting canceled", "meeting-deleted.ftl", model);
		
		List<String> emails = new ArrayList<>();
		for(User curator: curatorService.getByMeeting(meeting.getId()))
			emails.add(curator.getEmail());
		for(Student student: studentService.getByMeeting(meeting.getId()))
			emails.add(student.getEmail());
		
		mailService.send(mail, emails);
	}
	
	@Override
	public void onAttachmentAdded(Attachment attachment) {
		Map<String, Object> model = new HashMap<>();
		model.put("attachment", attachment);
		Mail mail = build("Collaboration Assistant - File attachment added", "attachment-added.ftl", model);
		
		List<String> emails = new ArrayList<>();
		Team team = teamService.getForAttachment(attachment.getId());
		if(team != null) {
			for(User curator: curatorService.getByTeam(team.getId()))
				emails.add(curator.getEmail());
			for(Student student: studentService.getByTeam(team.getId()))
				emails.add(student.getEmail());
		}
		else {
			Project project = projectService.getForAttachment(attachment.getId());
			for(User curator: curatorService.getByProject(project.getId()))
				emails.add(curator.getEmail());
			for(Student student: studentService.getByProject(project.getId()))
				emails.add(student.getEmail());
		}

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
