package com.netcracker.service;

import java.util.List;

import com.netcracker.model.Role;

public interface RoleService {

	List<Role> getAll();

	void add(Role role);

	void update(Role role);

	void delete(Role role);
}