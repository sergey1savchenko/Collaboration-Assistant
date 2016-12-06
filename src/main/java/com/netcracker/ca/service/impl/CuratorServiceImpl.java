package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.ca.dao.CuratorDao;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.NotificationService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.service.UserService;
import com.netcracker.ca.utils.ServiceException;

@Service
public class CuratorServiceImpl implements CuratorService {

	@Autowired
	private CuratorDao curatorDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private NotificationService notificationService;

	@Override
	public void add(int curatorId, int projectId, int teamId) {
		if(teamService.getCurrentForCurator(curatorId) != null)
			throw new ServiceException("Curator can have only one active project at a time");
		curatorDao.add(curatorId, projectId, teamId);
		
		User curator = userService.getById(curatorId);
		Project project = projectService.getById(projectId);
		Team team = teamService.getById(teamId);
		notificationService.onCuratorAddedToProject(curator, project, team);
	}
	
	@Override
	public void delete(int curatorId, int projectId) {
		curatorDao.delete(curatorId, projectId);
	}

	@Override
	public List<User> getByProject(int projectId) {
		return curatorDao.getByProject(projectId);
	}

	@Override
	public List<User> getByTeam(int teamId) { 
		return curatorDao.getByTeam(teamId);
	}

	@Override
	public List<User> getByMeeting(int meetingId) {
		return curatorDao.getByMeeting(meetingId);
	}

	@Override
	public List<User> getFreeCurators() {
		return curatorDao.getFreeCurators();
	}

}
