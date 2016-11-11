package com.netcracker.service;

import java.sql.SQLException;
import java.util.List;

import com.netcracker.model.Project;

public interface ProjectService {

	List<Project> getAll();

	Project getById(int id);

	void add(Project project) throws SQLException;

	void update(Project project) throws SQLException;

	void delete(int id) throws SQLException;
}