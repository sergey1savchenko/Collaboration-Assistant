package com.netcracker.service;

import com.netcracker.model.MeetingEvaluation;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface MeetingEvaluationService {

    void add(MeetingEvaluation me) throws SQLException;

    void update(MeetingEvaluation me) throws SQLException;

    void delete(int id) throws SQLException;

    List<MeetingEvaluation> getStudentsEvaluation(int id);

    List<MeetingEvaluation> getEvaluationByMeeting(int id);
}
