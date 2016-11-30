package com.netcracker.ca.service;

import java.util.List;
import java.util.Map;

import com.netcracker.ca.model.ProjectEvaluation;
import com.netcracker.ca.model.User;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface ProjectEvaluationService {

    void add(ProjectEvaluation pe, int studentId, int meetingId, int curatorId);

    void update(ProjectEvaluation pe);

    void delete(int id);
    
    void addAll(List<ProjectEvaluation> evals, int studentId, int projectId, int curatorId);
    
    List<ProjectEvaluation> getByStudentAndProjectAndCurator(int studentId, int projectId, int curatorId);
    
    Map<User, List<ProjectEvaluation>> getByStudentAndProjectPerCurator(int studentId, int projectId);

    //List<ProjectEvaluation> getEvaluationsOfTeam(int id);

    //List<ProjectEvaluation> getEvaluationsOfProject(int id);
}
