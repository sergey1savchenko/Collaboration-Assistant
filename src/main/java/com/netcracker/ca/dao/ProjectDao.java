package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Project;

public interface ProjectDao extends Dao<Project, Integer> {

	List<Project> getAll();
	
	List<Project> getAll(int limit, int offset);
	
	int count();
	
	Project getByTitle(String title);
	
}