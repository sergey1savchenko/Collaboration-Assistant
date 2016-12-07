package com.netcracker.ca.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.MeetingEvaluationDao;
import com.netcracker.ca.model.EvaluationScope;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MeetingEvaluation;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.service.MeetingEvaluationService;
import com.netcracker.ca.service.ProjectService;

/**
 * Created by Oleksandr on 12.11.2016.
 */

@Service
@Transactional
public class MeetingEvaluationServiceImpl implements MeetingEvaluationService {

    @Autowired
    private MeetingEvaluationDao meetingEvaluationDao;
    
    @Autowired
    private CuratorService curatorService;
    
    @Autowired
    private MarkTypeService markTypeService;
    
    @Autowired
    private ProjectService projectService;
    
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
		List<MeetingEvaluation> evals = meetingEvaluationDao.getByStudentAndMeetingAndCurator(studentId, meetingId, curatorId);
		if(evals.isEmpty()) {
			Project project = projectService.getForMeeting(meetingId);
			for(MarkType markType: markTypeService.getAllowed(project.getId(), EvaluationScope.MEETINGS)) {
				MeetingEvaluation eval = new MeetingEvaluation();
				eval.setMarktype(markType);
				evals.add(eval);
			}
			addAll(evals, studentId, meetingId, curatorId);
		}
		return evals;
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
