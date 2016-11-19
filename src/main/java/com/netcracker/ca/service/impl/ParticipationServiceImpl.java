package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.ca.dao.ParticipationDao;
import com.netcracker.ca.model.Participation;
import com.netcracker.ca.service.ParticipationService;

@Service
public class ParticipationServiceImpl implements ParticipationService {

	@Autowired
	private ParticipationDao participationDao;
	
	@Override
	public Participation getById(int id) {
		return participationDao.getById(id);
	}

	@Override
	public Participation getByStudentAndProject(int studentId, int projectId) {
		return participationDao.getByStudentAndProject(studentId, projectId);
	}

	@Override
	public List<Participation> getByStudent(int studentId) {
		return participationDao.getByStudent(studentId);
	}

	@Override
	public List<Participation> getByProject(int projectId) {
		return participationDao.getByProject(projectId);
	}

	@Override
	public List<Participation> getByTeam(int teamId) {
		return participationDao.getByTeam(teamId);
	}

	@Override
	public void add(Participation participation) {
		participationDao.add(participation);
	}

	@Override
	public void update(Participation participation) {
		participationDao.update(participation);
	}

	@Override
	public void delete(int id) {
		participationDao.delete(id);
	}

	@Override
	public void deleteByStudentAndProject(int studentId, int projectId) {
		participationDao.deleteByStudentAndProject(studentId, projectId);
	}

}
