package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Meeting;

/**
 * Created by Oleksandr on 10.11.2016.
 */
public interface MeetingDao {
	
	void addToProject(Meeting meeting, int projectId);
	
	void addToTeam(Meeting meeting, int teamId);
	
	void update(Meeting meeting);
	
	void delete(int id);
	
	Meeting getById(int id);

    List<Meeting> getAllTeamMeetings(int id);

    List<Meeting> getAllProjectMeetings(int id);
}
