package com.netcracker.ca.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.ca.dao.CuratorDao;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorService;

@Service
public class CuratorServiceImpl implements CuratorService {

	@Autowired
	private CuratorDao curatorDao;

	@Override
	public void add(int curatorId, int projectId, int teamId) {
		curatorDao.add(curatorId, projectId, teamId);
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
	public Map<Integer, List<User>> getByProjectInTeams(int projectId) {
		return curatorDao.getByProjectInTeams(projectId);
	}

	@Override
	public List<User> getByMeeting(int meetingId) {
		return curatorDao.getByMeeting(meetingId);
	}

}
