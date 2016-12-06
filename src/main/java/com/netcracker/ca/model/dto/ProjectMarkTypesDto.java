package com.netcracker.ca.model.dto;

import java.util.List;

import com.netcracker.ca.model.Project;

public class ProjectMarkTypesDto {

	private Project project;

	private List<Integer> projectMarkTypeIds;

	private List<Integer> meetingMarkTypeIds;
	
	public ProjectMarkTypesDto() {
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Integer> getProjectMarkTypeIds() {
		return projectMarkTypeIds;
	}

	public void setProjectMarkTypeIds(List<Integer> projectMarkTypeIds) {
		this.projectMarkTypeIds = projectMarkTypeIds;
	}

	public List<Integer> getMeetingMarkTypeIds() {
		return meetingMarkTypeIds;
	}

	public void setMeetingMarkTypeIds(List<Integer> meetingMarkTypeIds) {
		this.meetingMarkTypeIds = meetingMarkTypeIds;
	}
}