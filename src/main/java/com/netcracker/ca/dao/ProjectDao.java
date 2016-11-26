package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Project;

public interface ProjectDao extends Dao<Project, Integer> {

	List<Project> getAll();
	
	Project getByTitle(String title);
	
	void addCurator(int curatorId, int projectId, int teamId);
	
	void removeCurator(int curatorId, int projectId);
	
	Project getCurrentForStudent(int studentId);
	
	Project getCurrentForCurator(int curatorId);
}