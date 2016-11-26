package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Project;

public interface ProjectService {

	List<Project> getAll();

	Project getById(int id);
	
	Project getByTitle(String title);

	void add(Project project);

	void update(Project project);

	void delete(int id);
	
	void addByTemplate(Project projectNew, Project projectTemplate);

}