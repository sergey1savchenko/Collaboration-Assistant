package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.TeamDao;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.TeamService;

@Service
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDao teamDao;

	@Override
	public List<Team> getAll() {
		return teamDao.getAll();
	}

	@Override
	public Team getById(int id) {
		return teamDao.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Team team) {
		teamDao.add(team);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Team team) {
		teamDao.update(team);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) {
		teamDao.delete(id);
	}

	@Override
	public List<Team> getByProject(Project project) {
		return teamDao.getByProject(project);
	}

	@Override
	public Team getByTitle(String title) {
		return teamDao.getByTitle(title);
	}
}