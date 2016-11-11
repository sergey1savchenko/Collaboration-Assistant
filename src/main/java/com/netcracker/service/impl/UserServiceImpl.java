package com.netcracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.dao.UserDAO;
import com.netcracker.model.Role;
import com.netcracker.model.User;
import com.netcracker.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User getById(int id) {
		return userDAO.getById(id);
	}

	@Override
	public User getByEmail(String email) {
		return userDAO.getByEmail(email);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(User user) {
		userDAO.add(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void updateRoles(User user) {
		userDAO.updateRoles(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addRole(User user, Role role) {
		userDAO.addRole(user, role);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void deleteRole(User user, Role role) {
		userDAO.deleteRole(user, role);
	}
}