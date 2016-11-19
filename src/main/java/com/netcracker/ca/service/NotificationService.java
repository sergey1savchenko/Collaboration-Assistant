package com.netcracker.ca.service;

import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Meeting;
import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;

public interface NotificationService {

	void onProjectCreation(Project project);
	
	void onStudentAddedToProject(Student student, Project project, Team team);
	
	void onCuratorAddedToProject(User curator, Project project, Team team);
	
	void onMeetingCreated(Meeting meeting);
	
	void onMeetingEdited(Meeting meeting);
	
	void onMeetingDeleted(Meeting meeting);
	
	void onAttachmentAdded(Attachment attachment);
	
	void onProjectStatusChanged(Participation part);
	
}
