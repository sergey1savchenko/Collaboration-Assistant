package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.EvaluationScope;

public interface MarkTypeDao extends Dao<MarkType, Integer> {
	
	void allow(int markTypeId, int projectId, EvaluationScope scope);
	
	void disallow(int markTypeId, int projectId, EvaluationScope scope);
	
	List<MarkType> getAllowed(int projectId, EvaluationScope scope);
	
	List<MarkType> getAll();
	
	boolean isAllowed(int id);
	
	MarkType getByTitle(String title);
}