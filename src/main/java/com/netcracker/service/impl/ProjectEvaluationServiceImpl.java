package com.netcracker.service.impl;

import com.netcracker.dao.ProjectEvaluationDao;
import com.netcracker.model.ProjectEvaluation;
import com.netcracker.service.ProjectEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleksandr on 12.11.2016.
 */

@Service
@Transactional(readOnly = true)
public class ProjectEvaluationServiceImpl implements ProjectEvaluationService {

    @Autowired
    private ProjectEvaluationDao projectEvaluationDao;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add(ProjectEvaluation pe) throws SQLException {
        projectEvaluationDao.add(pe);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(ProjectEvaluation pe) throws SQLException {
        projectEvaluationDao.update(pe);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) throws SQLException {
        projectEvaluationDao.delete(id);
    }

    @Override
    public List<ProjectEvaluation> getEvaluationsOfTeam(int id) {
        return projectEvaluationDao.getEvaluationsOfTeam(id);
    }

    @Override
    public List<ProjectEvaluation> getEvaluationsOfProject(int id) {
        return projectEvaluationDao.getEvaluationsOfProject(id);
    }
}
