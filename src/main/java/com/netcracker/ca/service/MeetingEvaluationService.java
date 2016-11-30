package com.netcracker.ca.service;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.MeetingEvaluation;
import com.netcracker.ca.model.User;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface MeetingEvaluationService {

    void add(MeetingEvaluation me,  int studentId, int meetingId, int curatorId);

    void update(MeetingEvaluation me);

    void delete(int id);
    
    void addAll(List<MeetingEvaluation> evals, int studentId, int meetingId, int curatorId);
    
    List<MeetingEvaluation> getByStudentAndMeetingAndCurator(int studentId, int meetingId, int curatorId);
    
    Map<User, List<MeetingEvaluation>> getByStudentAndMeetingPerCurator(int studentId, int meetingId);

    //List<MeetingEvaluation> getStudentsEvaluation(int id);

    //List<MeetingEvaluation> getEvaluationByMeeting(int id);
}
