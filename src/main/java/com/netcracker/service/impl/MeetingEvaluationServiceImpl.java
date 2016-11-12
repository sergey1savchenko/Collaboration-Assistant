package com.netcracker.service.impl;

import com.netcracker.dao.MeetingEvaluationDao;
import com.netcracker.model.MeetingEvaluation;
import com.netcracker.service.MeetingEvaluationService;
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
public class MeetingEvaluationServiceImpl implements MeetingEvaluationService {

    @Autowired
    private MeetingEvaluationDao meetingEvaluationDao;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add(MeetingEvaluation me) throws SQLException {
        meetingEvaluationDao.add(me);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(MeetingEvaluation me) throws SQLException {
        meetingEvaluationDao.update(me);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) throws SQLException {
        meetingEvaluationDao.delete(id);
    }

    @Override
    public List<MeetingEvaluation> getStudentsEvaluation(int id) {
        return meetingEvaluationDao.getStudentsEvaluation(id);
    }

    @Override
    public List<MeetingEvaluation> getEvaluationByMeeting(int id) {
        return meetingEvaluationDao.getEvaluationByMeeting(id);
    }
}
