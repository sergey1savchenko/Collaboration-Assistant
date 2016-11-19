package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Role;

public interface RoleService {

	List<Role> getAll();
	
	Role getById(int id);
	
	Role getByName(String name);

	void add(Role role);

	void update(Role role);

	void delete(int id);
}