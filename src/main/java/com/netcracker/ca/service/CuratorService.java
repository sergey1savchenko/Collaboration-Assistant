package com.netcracker.ca.service;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.User;

public interface CuratorService {

	void add(int curatorId, int projectId, int teamId);
	
	void delete(int curatorId, int projectId);
	
	List<User> getByProject(int projectId);
	
	List<User> getByTeam(int teamId);
	
	Map<Integer, List<User>> getByProjectInTeams(int projectId);
	
}
