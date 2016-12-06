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
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.ProjectStatus;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.dto.ParticipationDto;
import com.netcracker.ca.service.NotificationService;
import com.netcracker.ca.service.ParticipationService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.StudentService;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.utils.ServiceException;

@Service
public class ParticipationServiceImpl implements ParticipationService {

	private static String DEFAULT_COMMENT = "Invited to project";
	private static ProjectStatus involved;

	@Autowired
	private ParticipationDao participationDao;

	@Autowired
	private ProjectStatusDao projectStatusDao;
	
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ProjectService projectService;
	
	
	@PostConstruct
	private void init() {
		involved = projectStatusDao.getByDesc("Involved");
	}

	@Override
	public Participation getByStudentAndProject(int studentId, int projectId) {
		return participationDao.getByStudentAndProject(studentId, projectId);
	}

	@Override
	public void add(Participation participation, int studentId, int projectId) {
		if(teamService.getCurrentForStudent(studentId) != null)
			throw new ServiceException("Student can have only one active project at a time");
		participationDao.add(participation, studentId, projectId);
		Student student = studentService.getById(studentId);
		Project project = projectService.getById(projectId);
		Team team = teamService.getById(participation.getTeam().getId());
		notificationService.onStudentAddedToProject(student, project, team);
	}

	@Override
	public void add(int teamId, int studentId, int projectId) {
		Participation part = new Participation();
		part.setTeam(new Team(teamId));
		part.setStatus(involved);
		part.setComment(DEFAULT_COMMENT);
		part.setAssigned(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
		add(part, studentId, projectId);
	}

	@Override
	public void update(Participation participation) {
		participationDao.update(participation);
	}

	@Override
	public void delete(int studentId, int projectId) {
		participationDao.delete(studentId, projectId);
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
