package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Project;

public interface ProjectDao {

	List<Project> getAll();

	Project getById(int id);
	
	Project getByTitle(String title);

	void add(Project project);

	void update(Project project);

	void delete(int id);
	
	void addCurator(int curatorId, int projectId, int teamId);
	
	void removeCurator(int curatorId, int projectId, int teamId);
}