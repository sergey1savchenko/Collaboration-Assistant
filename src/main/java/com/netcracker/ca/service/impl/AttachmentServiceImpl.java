package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.netcracker.ca.dao.AttachmentDao;
import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.dto.AttachmentDto;
import com.netcracker.ca.service.AttachmentService;
import com.netcracker.ca.service.StorageService;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private StorageService storageService;
	

	@Override
	public Attachment add(AttachmentDto attDto) {
		StringBuilder builder = new StringBuilder();
		if(attDto.getTeamId() != 0)
			builder.append("/team/").append(attDto.getTeamId());
		else
			builder.append("/project/").append(attDto.getProjectId());
		builder.append('/').append(attDto.getName());
		String link = builder.toString();
		storageService.store(attDto.getInput(), link);
		Attachment att = new Attachment();
		att.setTeam(new Team(attDto.getTeamId()));
		att.setMimeType(attDto.getMimeType());
		att.setText(attDto.getText());
		att.setLink(link);
		attachmentDao.add(att);
		return att;
	}
	
	@Override
	public Resource getAsResource(int id) {
		Attachment att = attachmentDao.getById(id);
		return storageService.retrieve(att.getLink());
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

}