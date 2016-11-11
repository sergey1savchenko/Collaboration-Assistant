package com.netcracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.dao.MarkTypeDAO;
import com.netcracker.model.MarkType;
import com.netcracker.model.MarkTypeScope;
import com.netcracker.model.Project;
import com.netcracker.service.MarkTypeService;

@Service
@Transactional(readOnly = true)
public class MarkTypeServiceImpl implements MarkTypeService {

	@Autowired
	private MarkTypeDAO markTypeDAO;

	@Override
	public MarkType getById(int id) {
		return markTypeDAO.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(MarkType markType) {
		markTypeDAO.add(markType);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(MarkType markType) {
		markTypeDAO.update(markType);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(MarkType markType) {
		markTypeDAO.delete(markType);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void allow(MarkType markType, Project project, MarkTypeScope scope) {
		markTypeDAO.allow(markType, project, scope);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void disallow(MarkType markType, Project project, MarkTypeScope scope) {
		markTypeDAO.disallow(markType, project, scope);
	}

	@Override
	public List<MarkType> getAllowed(Project project, MarkTypeScope scope) {
		return markTypeDAO.getAllowed(project, scope);
	}
}