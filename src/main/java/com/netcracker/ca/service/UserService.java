package com.netcracker.ca.service;

import com.netcracker.ca.model.User;

public interface UserService {

	User getById(int id);

	User getByEmail(String email);

	void add(User user);

	void update(User user);

	void updateRole(User user);
}