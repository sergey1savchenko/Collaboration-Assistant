package com.netcracker.ca.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.ProjectDao;
import com.netcracker.ca.model.MarkTypeScope;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.StudentService;
import com.netcracker.ca.service.TeamService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private MarkTypeService markTypeService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CuratorService curatorService;

	@Override
	public List<Project> getAll() {
		return projectDao.getAll();
	}

	@Override
	public Project getById(int id) {
		return projectDao.getById(id);
	}
	
	@Override
	public Project getByIdWithUsers(int id) {
		Project project = projectDao.getById(id);
		if(project != null) {
			project.setTeams(teamService.getByProject(project.getId()));
			Map<Integer, Team> teams = new HashMap<>();
			for(Team team: project.getTeams())
				teams.put(team.getId(), team);
			for(Entry<Integer, List<Student>> entry: studentService.getByProjectInTeams(project.getId()).entrySet())
				teams.get(entry.getKey()).setStudents(entry.getValue());
			for(Entry<Integer, List<User>> entry: curatorService.getByProjectInTeams(project.getId()).entrySet())
				teams.get(entry.getKey()).setCurators(entry.getValue());
		}
		return project;
	}
	
	@Override
	public void add(Project project, List<Integer> meetingMarkTypeIds, List<Integer> projectMarkTypeIds) {
		projectDao.add(project);
		markTypeService.allow(meetingMarkTypeIds, project.getId(), MarkTypeScope.MEETINGS);
		markTypeService.allow(projectMarkTypeIds, project.getId(), MarkTypeScope.PROJECTS);	
	}

	@Override
	public void update(Project project) {
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

}