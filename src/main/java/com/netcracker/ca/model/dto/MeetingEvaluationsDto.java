package com.netcracker.ca.model.dto;

import java.util.List;

import com.netcracker.ca.model.MeetingEvaluation;

public class MeetingEvaluationsDto {

	private List<MeetingEvaluation> meetingEvaluations;

	public List<MeetingEvaluation> getMeetingEvaluations() {
		return meetingEvaluations;
	}

	public void setMeetingEvaluations(List<MeetingEvaluation> meetingEvaluations) {
		this.meetingEvaluations = meetingEvaluations;
	}
	
}
