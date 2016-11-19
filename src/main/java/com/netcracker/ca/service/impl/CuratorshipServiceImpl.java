package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.ca.dao.ProjectDao;
import com.netcracker.ca.dao.UserDao;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorshipService;

@Service
public class CuratorshipServiceImpl implements CuratorshipService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProjectDao projectDao;

	@Override
	public void addToProject(int curatorId, int projectId, int teamId) {
		projectDao.addCurator(curatorId, projectId, teamId);
	}
	
	@Override
	public void removeFromProject(int curatorId, int projectId, int teamId) {
		projectDao.removeCurator(curatorId, projectId, teamId);
	}

	@Override
	public List<User> getByProject(int projectId) {
		return userDao.getCuratorsByProject(projectId);
	}

	@Override
	public List<User> getByTeam(int teamId) {
		return userDao.getCuratorsByTeam(teamId);
	}

	

}
