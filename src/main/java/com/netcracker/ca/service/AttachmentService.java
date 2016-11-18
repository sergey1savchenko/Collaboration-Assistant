package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Attachment;

public interface AttachmentService {

	Attachment getById(int id);

	void addToProject(Attachment attachment, int projectId);

	void addToTeam(Attachment attachment, int projectId, int teamId);

	void update(Attachment attachment);

	void delete(int id);

	List<Attachment> getTeamAttachments(int teamId);

	List<Attachment> getProjectAttachments(int projectId);
}