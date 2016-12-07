package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.ProjectDao;
import com.netcracker.ca.model.EvaluationScope;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.utils.ServiceException;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private MarkTypeService markTypeService;
	
	@Autowired
	private TeamService teamService;

	@Override
	public List<Project> getAll() {
		return projectDao.getAll();
	}

	@Override
	public Project getById(int id) {
		return projectDao.getById(id);
	}
	
	@Override
	public Project getByIdWithTeams(int id) {
		Project project = projectDao.getById(id);
		if(project != null) {
			project.setTeams(teamService.getByProject(project.getId()));
		}
		return project;
	}
	
	@Override
	public void add(Project project, List<Integer> meetingMarkTypeIds, List<Integer> projectMarkTypeIds) {
		if(projectDao.getByTitle(project.getTitle()) != null)
			throw new ServiceException("Project title must be unique");
		projectDao.add(project);
		markTypeService.allow(meetingMarkTypeIds, project.getId(), EvaluationScope.MEETINGS);
		markTypeService.allow(projectMarkTypeIds, project.getId(), EvaluationScope.PROJECTS);	
	}

	@Override
	public void update(Project project) {
		Project byTitle = projectDao.getByTitle(project.getTitle());
		if(byTitle != null && !byTitle.equals(project))
			throw new ServiceException("Project title must be unique");
		projectDao.update(project);
	}

	@Override
	public void delete(int id) {
		projectDao.delete(id);
	}

	@Override
	public List<Project> getAll(int limit, int offset) {
		return projectDao.getAll(limit, offset);
	}

	@Override
	public int count() {
		return projectDao.count();
	}

	@Override
	public Project getForAttachment(int attachmentId) {
		return projectDao.getForAttachment(attachmentId);
	}

	@Override
	public Project getForMeeting(int meetingId) {
		return projectDao.getForMeeting(meetingId);
	}

}