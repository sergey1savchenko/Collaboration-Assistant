package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;

public interface CuratorshipService {

	void addToProject(int curatorId, int projectId, int teamId);
	
	void removeFromProject(int curatorId, int projectId);
	
	List<User> getByProject(int projectId);
	
	List<User> getByTeam(int teamId);
	
	Team getCurrent(int curatorId);
	
}
