package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Role;

public interface RoleDao {

	List<Role> getAll();
	
	Role getById(int id);
	
	void add(Role role);
	
	void update(Role role);
	
	void delete(int id);
}