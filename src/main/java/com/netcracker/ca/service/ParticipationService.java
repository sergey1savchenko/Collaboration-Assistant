package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.dto.ParticipationDto;

public interface ParticipationService {
	
	Participation getByStudentAndProject(int studentId, int projectId);
	
	void add(Participation participation, int studentId, int projectId);
	
	void add(int teamId, int studentId, int projectId);
	
	void update(Participation participation);
	
	void delete(int studentId, int projectId);
	
	void setAll(List<ParticipationDto> partDtos, int projectId);
	
}
