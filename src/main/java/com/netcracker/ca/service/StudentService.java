package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Student;

public interface StudentService {

	Student getById(int id);

	Student getByAppFormId(int appFormId);

	void add(Student student);

	void update(Student student);
	
	List<Student> getByProject(int projectId);
	
	List<Student> getByTeam(int teamId);

}