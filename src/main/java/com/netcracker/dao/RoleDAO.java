package com.netcracker.dao;

import java.util.List;

import com.netcracker.model.Role;

public interface RoleDAO {

	List<Role> getAll();
	
	void add(Role role);
	
	void update(Role role);
	
	void delete(Role role);
}