package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.MeetingEvaluation;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface MeetingEvaluationDao {

    void add(MeetingEvaluation me);

    void update(MeetingEvaluation me);

    void delete(int id);

    List<MeetingEvaluation> getStudentsEvaluation(int id);

    List<MeetingEvaluation> getEvaluationByMeeting(int id);
}
