package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.MarkTypeDao;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.EvaluationScope;
import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.utils.ServiceException;

@Service
@Transactional
public class MarkTypeServiceImpl implements MarkTypeService {

	@Autowired
	private MarkTypeDao markTypeDao;

	@Override
	public MarkType getById(int id) {
		return markTypeDao.getById(id);
	}

	@Override
	public void add(MarkType markType) {
		markTypeDao.add(markType);
	}
	
	@Override
	public void update(MarkType markType) {
		markTypeDao.update(markType);
	}

	@Override
	public void delete(int id) {
		if (markTypeDao.isAllowed(id))
			throw new ServiceException("MarkType is already used in project(s)");
		markTypeDao.delete(id);
	}

	@Override
	public List<MarkType> getAll() {
		return markTypeDao.getAll();
	}

	@Override
	public List<MarkType> getAllowed(int projectId, EvaluationScope scope) {
		return markTypeDao.getAllowed(projectId, scope);
	}

	@Override
	public void allow(List<Integer> markTypeIds, int projectId, EvaluationScope scope) {
		for (Integer markTypeId : markTypeIds) {
			markTypeDao.allow(markTypeId, projectId, scope);
		}
	}
	
}