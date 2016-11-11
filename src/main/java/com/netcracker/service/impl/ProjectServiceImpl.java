package com.netcracker.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.dao.ProjectDAO;
import com.netcracker.model.Project;
import com.netcracker.service.ProjectService;

@Service
@Transactional (readOnly = true)
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;

	@Override
	public List<Project> getAll() {
		return projectDAO.getAll();
	}

	@Override
	public Project getById(int id) {
		return projectDAO.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Project project) throws SQLException {
		projectDAO.add(project);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Project project) throws SQLException {
		projectDAO.update(project);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) throws SQLException {
		projectDAO.delete(id);
	}
}