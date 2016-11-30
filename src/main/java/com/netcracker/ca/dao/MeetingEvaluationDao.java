package com.netcracker.ca.dao;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.MeetingEvaluation;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface MeetingEvaluationDao {
	
	void add(MeetingEvaluation me, int studentId, int meetingId, int curatorId);
	
	void addAll(List<MeetingEvaluation> me, int studentId, int meetingId, int curatorId);
	
	void update(MeetingEvaluation me);
	
	void delete(int id);
	
	MeetingEvaluation getById(int id);
	
	List<MeetingEvaluation> getByStudentAndMeetingAndCurator(int studentId, int meetingId, int curatorId);
	
	Map<Integer, List<MeetingEvaluation>> getByStudentAndMeetingPerCurator(int studentId, int meetingId);
}
