package com.netcracker.service;

import com.netcracker.model.Role;
import com.netcracker.model.User;

public interface UserService {

	User getById(int id);

	User getByEmail(String email);

	void add(User user);

	void update(User user);

	void updateRoles(User user);

	void addRole(User user, Role role);

	void deleteRole(User user, Role role);
}