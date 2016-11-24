package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.ProjectEvaluation;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface ProjectEvaluationDao extends Dao<ProjectEvaluation, Integer> {

    List<ProjectEvaluation> getEvaluationsOfTeam(int id);

    List<ProjectEvaluation> getEvaluationsOfProject(int id);
}
