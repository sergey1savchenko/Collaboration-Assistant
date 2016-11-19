package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Student;

public interface StudentDao {

	Student getById(int id);
	
	Student getByAppFormId(int appFormId);
	
	void add(Student student);
	
	void update(Student student);
	
	List<Student> getByProject(int projectId);
	
	List<Student> getByTeam(int teamId);

}