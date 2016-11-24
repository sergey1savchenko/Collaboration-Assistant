package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.User;

public interface UserDao extends Dao<User, Integer> {
	
	User getByEmail(String email);
	
	List<User> getByRole(String role);
	
	List<User> getCuratorsByProject(int projectId);
	
	List<User> getCuratorsByTeam(int teamId);

}