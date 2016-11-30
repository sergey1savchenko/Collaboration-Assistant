package com.netcracker.ca.model.dto;

import java.util.List;

import com.netcracker.ca.model.ProjectEvaluation;
import com.netcracker.ca.model.User;

public class CuratorProjectEvaluationsDto {

	private User curator;

	private List<ProjectEvaluation> evaluations;

	public CuratorProjectEvaluationsDto() {
	}

	public CuratorProjectEvaluationsDto(User curator, List<ProjectEvaluation> evaluations) {
		this.curator = curator;
		this.evaluations = evaluations;
	}

	public User getCurator() {
		return curator;
	}

	public void setCurator(User curator) {
		this.curator = curator;
	}

	public List<ProjectEvaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<ProjectEvaluation> evaluations) {
		this.evaluations = evaluations;
	}

}
