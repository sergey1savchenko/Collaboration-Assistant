package com.netcracker.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.dao.TeamDAO;
import com.netcracker.model.Project;
import com.netcracker.model.Team;
import com.netcracker.service.TeamService;

@Service
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDAO teamDAO;

	@Override
	public List<Team> getAll() {
		return teamDAO.getAll();
	}

	@Override
	public Team getById(int id) {
		return teamDAO.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Team team) throws SQLException {
		teamDAO.add(team);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Team team) throws SQLException {
		teamDAO.update(team);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) throws SQLException {
		teamDAO.delete(id);
	}

	@Override
	public List<Team> getByProject(Project project) {
		return teamDAO.getByProject(project);
	}

	@Override
	public Team getByTitle(String title) {
		return teamDAO.getByTitle(title);
	}
}