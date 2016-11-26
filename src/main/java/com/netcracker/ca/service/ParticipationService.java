package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.dto.ParticipationDto;

public interface ParticipationService {

	Participation getById(int id);
	
	Participation getByStudentAndProject(int studentId, int projectId);
	
	List<Participation> getByStudent(int studentId);
	
	List<Participation> getByProject(int projectId);
	
	List<Participation> getByTeam(int teamId);
	
	void add(Participation participation);
	
	void update(Participation participation);
	
	void delete(int id);
	
	void deleteByStudentAndProject(int studentId, int projectId);
	
	void updateAll(List<ParticipationDto> partDtos, int projectId);
	
}
