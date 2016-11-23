package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.MeetingEvaluation;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface MeetingEvaluationDao extends Dao<MeetingEvaluation, Integer> {

    List<MeetingEvaluation> getStudentsEvaluation(int id);

    List<MeetingEvaluation> getEvaluationByMeeting(int id);
}
