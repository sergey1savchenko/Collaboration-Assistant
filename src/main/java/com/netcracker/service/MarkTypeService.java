package com.netcracker.service;

import java.util.List;

import com.netcracker.model.MarkType;
import com.netcracker.model.MarkTypeScope;
import com.netcracker.model.Project;

public interface MarkTypeService {

	List<MarkType> getById(int id);

	void add(MarkType markType);

	void update(MarkType markType);

	void delete(MarkType markType);

	void allow(MarkType markType, Project project, int scope);

	void disallow(MarkType markType, Project project, MarkTypeScope scope);

	List<MarkType> getAllowed(Project project, MarkTypeScope scope);
}