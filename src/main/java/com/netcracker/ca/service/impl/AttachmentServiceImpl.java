package com.netcracker.ca.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.AttachmentDao;
import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.AttachmentService;
import com.netcracker.ca.service.NotificationService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.StorageService;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.utils.ServiceException;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;

	@Autowired
	private StorageService storageService;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private TeamService teamService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private NotificationService notificationService;

	@Override
	public Attachment addToProject(Attachment att, InputStream is, int projectId) throws IOException {
		if (!isExternal(att))
			att.setLink(buildLink(att.getLink(), projectId, false));
		if (attachmentDao.getByLinkForProject(att.getLink(), projectId) != null)
			throw new ServiceException("Project attachment already exists");
		if (!isExternal(att))
			storageService.store(is, att.getLink());
		attachmentDao.addToProject(att, projectId);
		notificationService.onAttachmentAddedToProject(att, projectId);
		return att;
	}

	@Override
	public Attachment addToTeam(Attachment att, InputStream is, int teamId) throws IOException {
		if (!isExternal(att))
			att.setLink(buildLink(att.getLink(), teamId, true));
		if (attachmentDao.getByLinkForTeam(att.getLink(), teamId) != null)
			throw new ServiceException("Team attachment already exists");
		if (!isExternal(att))
			storageService.store(is, att.getLink());
		attachmentDao.addToTeam(att, teamId);
		notificationService.onAttachmentAddedToTeam(att, teamId);
		return att;
	}

	@Override
	public Resource getAsResource(int id) {
		Attachment att = attachmentDao.getById(id);
		if (!isExternal(att))
			return storageService.retrieve(att.getLink());
		return resourceLoader.getResource(att.getLink());
	}

	@Override
	public void delete(int id) {
		Attachment att = attachmentDao.getById(id);
		if (!isExternal(att)) {
			if(!storageService.delete(att.getLink())) {
				throw new ServiceException("Failed to delete file");
			}
		}
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

	@Override
	public boolean belongsToTeam(int attachmentId, int teamId) {
		Team team = teamService.getForAttachment(attachmentId);
		return team != null && team.getId() == teamId;
	}

	@Override
	public boolean belongsToProject(int attachmentId, int projectId) {
		Project project = projectService.getForAttachment(attachmentId);
		return project.getId() == projectId;
	}

	private String buildLink(String name, int id, boolean isTeamAtt) {
		StringBuilder link = new StringBuilder(isTeamAtt ? "/team/" : "/project/").append(id).append('/').append(name);
		return link.toString();
	}

	private boolean isExternal(Attachment att) {
		return att.getLink().startsWith("http://") || att.getLink().startsWith("https://");
	}

}