package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.User;

public interface CuratorService {

	void add(int curatorId, int projectId, int teamId);
	
	void delete(int curatorId, int projectId);
	
	List<User> getByProject(int projectId);
	
	List<User> getByTeam(int teamId);
	
	List<User> getByMeeting(int meetingId);
	
}
