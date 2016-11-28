package com.netcracker.ca.service;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;

public interface StudentService {

	Student getById(int id);

	Student getByAppFormId(int appFormId);

	void add(Student student);

	void update(Student student);
	
	List<Student> getByProject(int projectId);
	
	List<Student> getByTeam(int teamId);
	
	Map<Integer, List<Student>> getByProjectInTeams(int projectId);

}