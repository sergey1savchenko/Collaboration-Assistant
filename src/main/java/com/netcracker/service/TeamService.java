package com.netcracker.service;

import java.sql.SQLException;
import java.util.List;

import com.netcracker.model.Project;
import com.netcracker.model.Team;

public interface TeamService {

	List<Team> getAll();

	Team getById(int id);

	void add(Team team) throws SQLException;

	void update(Team team) throws SQLException;

	void delete(int id) throws SQLException;

	List<Team> getByProject(Project project);

	Team getByTitle(String title);
}