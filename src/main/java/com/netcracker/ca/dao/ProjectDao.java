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
}