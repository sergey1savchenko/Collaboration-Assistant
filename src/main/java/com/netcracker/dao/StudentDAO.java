package com.netcracker.dao;

import java.util.List;
import java.util.Map;

import com.netcracker.model.Project;
import com.netcracker.model.Student;
import com.netcracker.model.StudentProjectStatus;
import com.netcracker.model.StudentProjectStatusType;

public interface StudentDAO {

	Student getById(int id);
	
	Student getByAppFormId(int appFormId);
	
	void add(Student student);
	
	void update(Student student);
	
	Map<Student, StudentProjectStatusType> getByProject(Project project);
	
	List<StudentProjectStatus> getProjectStatuses(Student student, Project project);
}