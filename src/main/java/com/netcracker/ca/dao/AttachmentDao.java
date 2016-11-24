package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Attachment;

public interface AttachmentDao extends Dao<Attachment, Integer> {
	
	List<Attachment> getTeamAttachments(int teamId);
	
	List<Attachment> getProjectAttachments(int projectId);
}