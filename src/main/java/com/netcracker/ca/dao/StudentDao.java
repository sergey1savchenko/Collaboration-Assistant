package com.netcracker.ca.dao;

import com.netcracker.ca.model.Student;

public interface StudentDao {

	Student getById(int id);
	
	Student getByAppFormId(int appFormId);
	
	void add(Student student);
	
	void update(Student student);

}