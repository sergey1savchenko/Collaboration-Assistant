package loki.ca.dao;

import java.util.List;

import loki.ca.model.Role;

public interface RoleDao {

	List<Role> getAllRoles();
	
	void addRole(Role role);
	
	void updateRole(Role role);
	
	void deleteRole(Role role);
}
