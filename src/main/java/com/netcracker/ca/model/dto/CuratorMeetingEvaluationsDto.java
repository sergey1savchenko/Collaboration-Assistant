package com.netcracker.ca.model.dto;

import java.util.List;

import com.netcracker.ca.model.MeetingEvaluation;
import com.netcracker.ca.model.User;

public class CuratorMeetingEvaluationsDto {

	private User curator;
	
	private List<MeetingEvaluation> evaluations;
	

	public CuratorMeetingEvaluationsDto() {
	}

	public CuratorMeetingEvaluationsDto(User curator, List<MeetingEvaluation> evaluations) {
		this.curator = curator;
		this.evaluations = evaluations;
	}

	public User getCurator() {
		return curator;
	}

	public void setCurator(User curator) {
		this.curator = curator;
	}

	public List<MeetingEvaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<MeetingEvaluation> evaluations) {
		this.evaluations = evaluations;
	}
	
}
