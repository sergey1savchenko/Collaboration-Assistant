package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Team;

public interface TeamService {

	Team getById(int id);

	void add(Team team);

	void update(Team team);

	void delete(int id);
	
	List<Team> getAll();

	List<Team> getByProject(int projectId);

	Team getByTitle(String title);
	
	Team getCurrentForStudent(int studentId);
	
	Team getCurrentForCurator(int curatorId);
}