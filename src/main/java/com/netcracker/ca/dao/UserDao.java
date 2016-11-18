package com.netcracker.ca.dao;

import com.netcracker.ca.model.User;

public interface UserDao {

	User getById(int id);
	
	User getByEmail(String email);
	
	void add(User user);
	
	void update(User user);
	
	void updateRole(User user);

}