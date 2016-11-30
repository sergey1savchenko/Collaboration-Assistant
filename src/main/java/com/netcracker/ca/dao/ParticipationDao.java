package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Participation;

public interface ParticipationDao {
	
	void add(Participation participation, int studentId, int projectId);
	
	void update(Participation participation);
	
	void delete(int id);
	
	Participation getById(int id);

	Participation getByStudentAndProject(int studentId, int projectId);
	
	List<Participation> getByProject(int projectId);
}
