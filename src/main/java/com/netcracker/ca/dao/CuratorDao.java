package com.netcracker.ca.dao;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.User;

public interface CuratorDao {

	void add(int curatorId, int projectId, int teamId);
	
	void delete(int curatorId, int projectId);
	
	List<User> getByTeam(int teamId);
	
	List<User> getByProject(int projectId);
	
	Map<Integer, List<User>> getByProjectInTeams(int projectId);
	
}
