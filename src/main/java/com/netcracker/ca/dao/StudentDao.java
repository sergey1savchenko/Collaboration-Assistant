package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Student;

public interface StudentDao extends Dao<Student, Integer> {
	
	Student getByAppFormId(int appFormId);
	
	List<Student> getByProject(int projectId);
	
	List<Student> getByTeam(int teamId);
	
}