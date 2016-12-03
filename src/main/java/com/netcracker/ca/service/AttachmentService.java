package com.netcracker.ca.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;

import com.netcracker.ca.model.Attachment;

public interface AttachmentService {

	Attachment addToProject(Attachment att, InputStream is, int projectId) throws IOException;
	
	Attachment addToTeam(Attachment att, InputStream is, int teamId) throws IOException;
	
	Resource getAsResource(int id);

	void delete(int id);

	List<Attachment> getTeamAttachments(int teamId);

	List<Attachment> getProjectAttachments(int projectId);
	
	boolean belongsToTeam(int attachmentId, int teamId);
	
	boolean belongsToProject(int attachmentId, int projectId);

}