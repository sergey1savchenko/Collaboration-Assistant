package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.ProjectDao;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MarkTypeScope;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.service.ProjectService;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private MarkTypeService markTypeService;

	@Override
	public List<Project> getAll() {
		return projectDao.getAll();
	}

	@Override
	public Project getById(int id) {
		return projectDao.getById(id);
	}

	@Override
	public Project getByTitle(String title) {
		return projectDao.getByTitle(title);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Project project) {
		projectDao.add(project);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addByTemplate(Project projectNew, Project projectTemplate) {
		projectDao.add(projectNew);
		for (MarkType mtype: markTypeService.getAllowed(projectTemplate.getId(), MarkTypeScope.MEETINGS))
			markTypeService.allow(mtype.getId(), projectNew.getId(), MarkTypeScope.MEETINGS);
		for (MarkType mtype : markTypeService.getAllowed(projectTemplate.getId(), MarkTypeScope.PROJECTS))
			markTypeService.allow(mtype.getId(), projectNew.getId(), MarkTypeScope.PROJECTS);
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Project project) {
		projectDao.update(project);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) {
		projectDao.delete(id);
	}
}