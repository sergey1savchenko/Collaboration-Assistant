package com.netcracker.ca.dao;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.ProjectEvaluation;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface ProjectEvaluationDao {
	
	void add(ProjectEvaluation pe, int studentId, int projectId, int curatorId);
	
	void addAll(List<ProjectEvaluation> pe, int studentId, int projectId, int curatorId);
	
	void update(ProjectEvaluation pe);
	
	void delete(int id);
	
	ProjectEvaluation getById(int id);
	
	List<ProjectEvaluation> getByStudentAndProjectAndCurator(int studentId, int projectId, int curatorId);
	
	Map<Integer, List<ProjectEvaluation>> getByStudentAndProjectPerCurator(int studentId, int projectId);
	
}
