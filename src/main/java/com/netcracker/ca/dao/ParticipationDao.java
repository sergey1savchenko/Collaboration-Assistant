package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Participation;

public interface ParticipationDao extends Dao<Participation, Integer> {

	Participation getByStudentAndProject(int studentId, int projectId);
	
	List<Participation> getByStudent(int studentId);
	
	List<Participation> getByProject(int projectId);
	
	List<Participation> getByTeam(int teamId);
	
	void deleteByStudentAndProject(int studentId, int projectId);
}
