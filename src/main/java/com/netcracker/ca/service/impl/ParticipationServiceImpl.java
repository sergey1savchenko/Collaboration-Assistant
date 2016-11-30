package com.netcracker.ca.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.ca.dao.ParticipationDao;
import com.netcracker.ca.dao.ProjectStatusDao;
import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.ProjectStatus;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.dto.ParticipationDto;
import com.netcracker.ca.service.ParticipationService;

@Service
public class ParticipationServiceImpl implements ParticipationService {

	private static String DEFAULT_COMMENT = "Invited to project";
	private static ProjectStatus DEFAULT_STATUS;

	@Autowired
	private ParticipationDao participationDao;

	@Autowired
	private ProjectStatusDao projectStatusDao;

	@PostConstruct
	private void init() {
		DEFAULT_STATUS = projectStatusDao.getByDesc("Invloved");
	}

	@Override
	public Participation getByStudentAndProject(int studentId, int projectId) {
		return participationDao.getByStudentAndProject(studentId, projectId);
	}

	@Override
	public void add(Participation participation, int studentId, int projectId) {
		participationDao.add(participation, studentId, projectId);
	}

	@Override
	public void add(int teamId, int studentId, int projectId) {
		Participation part = new Participation();
		part.setTeam(new Team(teamId));
		part.setStatus(DEFAULT_STATUS);
		part.setComment(DEFAULT_COMMENT);
		part.setAssigned(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
		participationDao.add(part, studentId, projectId);
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
	public void setAll(List<ParticipationDto> partDtos, int projectId) {
		List<Participation> projectParts = participationDao.getByProject(projectId);
		for (ParticipationDto partDto : partDtos) {
			int index = projectParts.indexOf(new Participation(partDto.getId()));
			// add new element
			if (index == -1) {
				add(partDto.getTeamId(), partDto.getStudentId(), projectId);
			} else {
				// update existing element
				// only team update here
				Participation part = projectParts.get(index);
				part.setTeam(new Team(partDto.getTeamId()));
				participationDao.update(part);
			}
		}
		for (Participation part : projectParts) {
			if (!partDtos.contains(new ParticipationDto(part.getId())))
				participationDao.delete(part.getId());
		}
	}

}
