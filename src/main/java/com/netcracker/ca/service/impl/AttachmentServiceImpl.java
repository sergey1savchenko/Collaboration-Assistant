package com.netcracker.ca.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.AttachmentDao;
import com.netcracker.ca.model.Attachment;
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
	public Attachment add(Attachment att, InputStream is) {
		storageService.store(is, att.getLink());
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