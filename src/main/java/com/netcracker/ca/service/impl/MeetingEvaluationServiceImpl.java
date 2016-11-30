package com.netcracker.ca.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.MeetingEvaluationDao;
import com.netcracker.ca.model.MeetingEvaluation;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.MeetingEvaluationService;

/**
 * Created by Oleksandr on 12.11.2016.
 */

@Service
@Transactional(readOnly = true)
public class MeetingEvaluationServiceImpl implements MeetingEvaluationService {

    @Autowired
    private MeetingEvaluationDao meetingEvaluationDao;
    
    @Autowired
    private CuratorService curatorService;
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add(MeetingEvaluation me, int studentId, int meetingId, int curatorId) {
        meetingEvaluationDao.add(me, studentId, meetingId, curatorId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(MeetingEvaluation me) {
        meetingEvaluationDao.update(me);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) {
        meetingEvaluationDao.delete(id);
    }

	@Override
	public void addAll(List<MeetingEvaluation> evals, int studentId, int meetingId, int curatorId) {
		 meetingEvaluationDao.addAll(evals, studentId, meetingId, curatorId);
	}

	@Override
	public List<MeetingEvaluation> getByStudentAndMeetingAndCurator(int studentId, int meetingId, int curatorId) {
		return meetingEvaluationDao.getByStudentAndMeetingAndCurator(studentId, meetingId, curatorId);
	}

	@Override
	public Map<User, List<MeetingEvaluation>> getByStudentAndMeetingPerCurator(int studentId, int meetingId) {
		Map<Integer, List<MeetingEvaluation>> map = meetingEvaluationDao.getByStudentAndMeetingPerCurator(studentId, meetingId);
		Map<User, List<MeetingEvaluation>> result = new HashMap<>();
		for(User curator: curatorService.getByMeeting(meetingId)) {
			result.put(curator, map.get(curator.getId()));
		}
		return result;
	}

}
