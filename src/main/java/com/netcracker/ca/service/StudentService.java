package com.netcracker.ca.service;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.Student;

public interface StudentService {

	Student getById(int id);

	Student getByAppFormId(int appFormId);

	void add(Student student);

	void update(Student student);
	
	List<Student> getByProject(int projectId);
	
	List<Student> getByTeam(int teamId);
	
	Map<Student, Participation> getByProjectWithParticipation(int projectId);
	
	Map<Student, Participation> getByTeamWithParticipation(int teamId);
	
	List<Student> getByTeamAndStatus(int teamId, int statusId);
	
	List<Student> getByMeeting(int meetingId);

}