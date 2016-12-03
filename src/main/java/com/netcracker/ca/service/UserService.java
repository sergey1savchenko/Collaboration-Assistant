package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.User;

public interface UserService {

	User getById(int id);

	User getByEmail(String email);

	void add(User user);

	void update(User user);
	
	List<User> getByRole(String role);
	
}