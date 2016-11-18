package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.AttachmentDao;
import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.service.AttachmentService;

@Service
@Transactional(readOnly = true)
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment getById(int id) {
		return attachmentDao.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addToProject(Attachment attachment, int projectId) {
		attachmentDao.addToProject(attachment, projectId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addToTeam(Attachment attachment, int projectId, int teamId) {
		attachmentDao.addToTeam(attachment, projectId, teamId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Attachment attachment) {
		attachmentDao.update(attachment);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) {
		attachmentDao.delete(id);
	}

	@Override
	public List<Attachment> getTeamAttachments(int teamId) {
		return attachmentDao.getTeamAttachments(teamId);
	}

	@Override
	public List<Attachment> getProjectAttachments(int projectId) {
		return attachmentDao.getProjectAttachments(projectId);
	}
}