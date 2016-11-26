package com.netcracker.ca.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.ca.dao.ParticipationDao;
import com.netcracker.ca.dao.ProjectStatusDao;
import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.ProjectStatus;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.dto.ParticipationDto;
import com.netcracker.ca.service.ParticipationService;

@Service
public class ParticipationServiceImpl implements ParticipationService {

	@Autowired
	private ParticipationDao participationDao;
	
	@Autowired 
	private ProjectStatusDao projectStatusDao;
	
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

	@Override
	public void updateAll(List<ParticipationDto> partDtos, int projectId) {
		ProjectStatus invitedStatus = projectStatusDao.getByDesc("Involved");
		for(ParticipationDto partDto: partDtos) {
			if(partDto.getId() == 0) {
				Participation p = new Participation();
				p.setStudent(new Student(partDto.getStudentId()));
				p.setTeam(new Team(partDto.getTeamId()));
				p.setProject(new Project(projectId));
				p.setStatus(invitedStatus);
				p.setComment("Invited to project");
				p.setAssigned(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
				participationDao.add(p);
			}
			else {
				// only team update for now 
				Participation p = participationDao.getById(partDto.getId());
				p.setTeam(new Team(partDto.getTeamId()));
				participationDao.update(p);
			}
		}
		for(Participation p: participationDao.getByProject(projectId))
			if(!partDtos.contains(new ParticipationDto(p.getId())))
				participationDao.delete(p.getId());
	}

}
