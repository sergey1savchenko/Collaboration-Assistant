package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MarkTypeScope;

public interface MarkTypeService {

	MarkType getById(int id);

	void add(MarkType markType);
	
	void update(MarkType markType);
	
	void delete(int id);
	
	void allow(List<Integer> markTypeIds, int projectId, MarkTypeScope scope);

	List<MarkType> getAll();
	
	List<MarkType> getAllowed(int projectId, MarkTypeScope scope);
}