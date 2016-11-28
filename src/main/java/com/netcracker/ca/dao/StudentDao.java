package com.netcracker.ca.dao;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.Student;

public interface StudentDao extends Dao<Student, Integer> {
	
	Student getByAppFormId(int appFormId);
	
	List<Student> getByTeam(int teamId);
	
	List<Student> getByProject(int projectId);
	
	Map<Integer, List<Student>> getByProjectInTeams(int projectId);
	
}