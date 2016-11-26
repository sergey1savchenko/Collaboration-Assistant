package com.netcracker.ca.dao;

import com.netcracker.ca.model.ProjectStatus;

public interface ProjectStatusDao extends Dao<ProjectStatus, Integer>{

	ProjectStatus getByDesc(String desc);
}
