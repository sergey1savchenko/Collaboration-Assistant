package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;

public interface TeamService {

	List<Team> getAll();

	Team getById(int id);

	void add(Team team);

	void update(Team team);

	void delete(int id);

	List<Team> getByProject(Project project);

	Team getByTitle(String title);
}