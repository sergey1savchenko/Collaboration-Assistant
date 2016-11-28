package com.netcracker.ca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.UserDao;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.StudentService;
import com.netcracker.ca.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CuratorService curatorService;
	
	@Override
	public User getById(int id) {
		return userDao.getById(id);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> getByRole(String role) {
		return userDao.getByRole(role);
	}

	@Override
	public List<User> getAssociatedWithProject(int projectId) {
		List<User> users = new ArrayList<>();
		users.addAll(curatorService.getByProject(projectId));
		users.addAll(studentService.getByProject(projectId));
		return users;
	}

	@Override
	public List<User> getAssociatedWithTeam(int teamId) {
		List<User> users = new ArrayList<>();
		users.addAll(curatorService.getByTeam(teamId));
		users.addAll(studentService.getByTeam(teamId));
		return users;
	}
	
}