package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.MarkTypeDao;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MarkTypeScope;
import com.netcracker.ca.service.MarkTypeService;

@Service
@Transactional(readOnly = true)
public class MarkTypeServiceImpl implements MarkTypeService {

	@Autowired
	private MarkTypeDao markTypeDao;

	@Override
	public MarkType getById(int id) {
		return markTypeDao.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(MarkType markType) {
		markTypeDao.add(markType);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(MarkType markType) {
		markTypeDao.update(markType);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) {
		markTypeDao.delete(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void allow(int markTypeId, int projectId, MarkTypeScope scope) {
		markTypeDao.allow(markTypeId, projectId, scope);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void disallow(int markTypeId, int projectId, MarkTypeScope scope) {
		markTypeDao.disallow(markTypeId, projectId, scope);
	}

	@Override
	public List<MarkType> getAllowed(int projectId, MarkTypeScope scope) {
		return markTypeDao.getAllowed(projectId, scope);
	}
}