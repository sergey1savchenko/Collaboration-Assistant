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
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.StorageService;
import com.netcracker.ca.service.TeamService;

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
	
	@Override
	public Attachment addToProject(Attachment att, InputStream is, int projectId) throws IOException {
		if(!isExternal(att)) {
			att.setLink(buildLink(att.getLink(), projectService.getById(projectId)));
			storageService.store(is, att.getLink());
		}
		attachmentDao.addToProject(att, projectId);
		return att;
	}
	
	@Override
	public Attachment addToTeam(Attachment att, InputStream is, int teamId) throws IOException {
		if(!isExternal(att)) {
			att.setLink(buildLink(att.getLink(), teamService.getByIdWithProject(teamId)));
			storageService.store(is, att.getLink());
		}
		attachmentDao.addToTeam(att, teamId);
		return att;
	}
	
	@Override
	public Resource getAsResource(int id) {
		Attachment att = attachmentDao.getById(id);
		if(!isExternal(att))
			return storageService.retrieve(att.getLink());
		return resourceLoader.getResource(att.getLink());
	}
	
	@Override
	public void delete(int id) {
		Attachment att = attachmentDao.getById(id);
		storageService.delete(att.getLink());
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
	
	private String buildLink(String name, Team team) {
		StringBuilder link = new StringBuilder("/").append(team.getProject().getTitle())
				.append("/").append(team.getTitle()).append('/').append(name);
		return link.toString();
	}

	private String buildLink(String name, Project project) {
		StringBuilder link = new StringBuilder("/").append(project.getTitle()).append('/')
				.append(name);
		return link.toString();
	}
	
	private boolean isExternal(Attachment att) {
		return att.getLink().startsWith("http://") || att.getLink().startsWith("https://");
	}


}