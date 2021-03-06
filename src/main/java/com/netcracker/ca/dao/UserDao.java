package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.User;
import com.netcracker.ca.model.UserAuth;

public interface UserDao extends Dao<User, Integer> {
	
	User getByEmail(String email);
	
	UserAuth getUserAuth(String email);
	
	List<User> getByRole(String role);
}
