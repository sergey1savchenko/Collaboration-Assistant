package loki.ca.dao;

import loki.ca.model.Role;
import loki.ca.model.User;

public interface UserDao {

	User getUserById(int id);
	
	User getUserByEmail(String email);
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void updateUserRoles(User user);
	
	void addRoleToUser(User user, Role role);
	
	void deleteRoleFromUser(User user, Role role);
}
