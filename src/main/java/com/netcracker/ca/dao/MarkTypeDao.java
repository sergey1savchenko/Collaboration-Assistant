package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MarkTypeScope;

public interface MarkTypeDao extends Dao<MarkType, Integer> {
	
	void allow(int markTypeId, int projectId, MarkTypeScope scope);
	
	void disallow(int markTypeId, int projectId, MarkTypeScope scope);
	
	List<MarkType> getAll();
	
	List<MarkType> getAllowed(int projectId, MarkTypeScope scope);	
	
	boolean isAllowed(int id);
}