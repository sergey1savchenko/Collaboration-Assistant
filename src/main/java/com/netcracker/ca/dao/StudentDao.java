package com.netcracker.ca.dao;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.Student;

public interface StudentDao extends Dao<Student, Integer> {
	
	Student getByAppFormId(int appFormId);
	
	List<Student> getByTeam(int teamId);
	
	List<Student> getByProject(int projectId);
	
	Map<Student, Participation> getByProjectWithParticipation(int projectId);
	
	Map<Student, Participation> getByTeamWithParticipation(int teamId);
	
	List<Student> getByTeamAndStatus(int teamId, int statusId);
	
	List<Student> getByMeeting(int meetingId);

	List<Student> getFreeStudents();
}