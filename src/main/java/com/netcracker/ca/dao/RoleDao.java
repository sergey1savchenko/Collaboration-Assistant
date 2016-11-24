package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Role;

public interface RoleDao extends Dao<Role, Integer> {

	List<Role> getAll();
	
	Role getByName(String name);
	
}