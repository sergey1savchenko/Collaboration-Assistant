package com.netcracker.service;

import com.netcracker.model.ProjectEvaluation;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleksandr on 12.11.2016.
 */
public interface ProjectEvaluationService {

    void add(ProjectEvaluation pe) throws SQLException;

    void update(ProjectEvaluation pe) throws SQLException;

    void delete(int id) throws SQLException;

    List<ProjectEvaluation> getEvaluationsOfTeam(int id);

    List<ProjectEvaluation> getEvaluationsOfProject(int id);
}
