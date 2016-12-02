package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Team;

public interface TeamDao extends Dao<Team, Integer> {
	
	List<Team> getAll();

	List<Team> getByProject(int projectId);
	
	Team getByTitle(String title);
	
	Team getByMeeting(int meetingId);
	
	Team getForAttachment(int attachmentId);
	
	Team getCurrentForStudent(int studentId);
	
	Team getCurrentForCurator(int curatorId);
}