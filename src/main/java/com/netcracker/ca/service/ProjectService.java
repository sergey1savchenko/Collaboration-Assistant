package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Project;

public interface ProjectService {

	List<Project> getAll();

	Project getById(int id);
	
	Project getByIdWithTeams(int id);
	
	void add(Project project, List<Integer> meetingMarkTypeIds, List<Integer> projectMarkTypeIds);

	void update(Project project);

	void delete(int id);
	
	List<Project> getAll(int limit, int offset);
	
	int count();
	
	Project getForAttachment(int attachmentId);

}