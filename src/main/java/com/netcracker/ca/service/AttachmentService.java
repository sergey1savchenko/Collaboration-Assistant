package com.netcracker.ca.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;

import com.netcracker.ca.model.Attachment;

public interface AttachmentService {

	Attachment add(Attachment att, InputStream is);
	
	Resource getAsResource(int id);

	void delete(int id);

	List<Attachment> getTeamAttachments(int teamId);

	List<Attachment> getProjectAttachments(int projectId);
}