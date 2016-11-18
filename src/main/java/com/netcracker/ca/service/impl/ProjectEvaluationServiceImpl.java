package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.ProjectEvaluationDao;
import com.netcracker.ca.model.ProjectEvaluation;
import com.netcracker.ca.service.ProjectEvaluationService;

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
    public void add(ProjectEvaluation pe) {
        projectEvaluationDao.add(pe);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(ProjectEvaluation pe) {
        projectEvaluationDao.update(pe);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) {
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
