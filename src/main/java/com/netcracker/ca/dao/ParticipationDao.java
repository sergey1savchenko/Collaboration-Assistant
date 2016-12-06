package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Participation;

public interface ParticipationDao {
	
	void add(Participation participation, int studentId, int projectId);
	
	void update(Participation participation);
	
	void delete(int id);
	
	void delete(int studentId, int projectId);
	
	Participation getById(int id);

	Participation getByStudentAndProject(int studentId, int projectId);
	
	List<Participation> getByProject(int projectId);
}
