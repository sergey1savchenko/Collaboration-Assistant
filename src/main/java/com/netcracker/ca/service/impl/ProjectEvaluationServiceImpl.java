package com.netcracker.ca.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.ProjectEvaluationDao;
import com.netcracker.ca.model.ProjectEvaluation;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.ParticipationService;
import com.netcracker.ca.service.ProjectEvaluationService;

/**
 * Created by Oleksandr on 12.11.2016.
 */

@Service
@Transactional(readOnly = true)
public class ProjectEvaluationServiceImpl implements ProjectEvaluationService {

	@Autowired
	private ProjectEvaluationDao projectEvaluationDao;
	
	@Autowired
	private CuratorService curatorService;
	
	@Autowired
	private ParticipationService partService;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(ProjectEvaluation pe, int studentId, int projectId, int curatorId) {
		projectEvaluationDao.add(pe, studentId, projectId, curatorId);
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
	public void addAll(List<ProjectEvaluation> evals, int studentId, int projectId, int curatorId) {
		projectEvaluationDao.addAll(evals, studentId, projectId, curatorId);
	}

	@Override
	public List<ProjectEvaluation> getByStudentAndProjectAndCurator(int studentId, int projectId, int curatorId) {
		return projectEvaluationDao.getByStudentAndProjectAndCurator(studentId, projectId, curatorId);
	}

	@Override
	public Map<User, List<ProjectEvaluation>> getByStudentAndProjectPerCurator(int studentId, int projectId) {
		Map<Integer, List<ProjectEvaluation>> map = projectEvaluationDao.getByStudentAndProjectPerCurator(studentId, projectId);
		Map<User, List<ProjectEvaluation>> result = new HashMap<>();
		for(User curator: curatorService.getByTeam(partService.getByStudentAndProject(studentId, projectId).getTeam().getId())) {
			result.put(curator, map.get(curator.getId()));
		}
		return result;
	}
	 
}
