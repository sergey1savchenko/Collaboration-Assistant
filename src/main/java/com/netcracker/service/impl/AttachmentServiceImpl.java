package com.netcracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.dao.AttachmentDAO;
import com.netcracker.model.Attachment;
import com.netcracker.model.Project;
import com.netcracker.model.Team;
import com.netcracker.service.AttachmentService;

@Service
@Transactional(readOnly = true)
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDAO attachmentDAO;

	@Override
	public Attachment getById(int id) {
		return attachmentDAO.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addToProject(Attachment attachment, Project project) {
		attachmentDAO.addToProject(attachment, project);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addToTeam(Attachment attachment, Team team) {
		attachmentDAO.addToTeam(attachment, team);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Attachment attachment) {
		attachmentDAO.update(attachment);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(Attachment attachment) {
		attachmentDAO.delete(attachment);
	}

	@Override
	public List<Attachment> getTeamAttachments(Team team) {
		return attachmentDAO.getTeamAttachments(team);
	}

	@Override
	public List<Attachment> getProjectAttachments(Project project) {
		return attachmentDAO.getProjectAttachments(project);
	}
}