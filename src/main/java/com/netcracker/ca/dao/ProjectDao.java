package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Project;

public interface ProjectDao extends Dao<Project, Integer> {

	List<Project> getAll();
	
	List<Project> getAll(int limit, int offset);
	
	Project getByTitle(String title);
	
	boolean existsWithTitle(String title);
	
	void addCurator(int curatorId, int projectId, int teamId);
	
	void removeCurator(int curatorId, int projectId);
	
}