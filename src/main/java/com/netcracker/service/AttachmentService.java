package com.netcracker.service;

import java.util.List;

import com.netcracker.model.Attachment;
import com.netcracker.model.Project;
import com.netcracker.model.Team;

public interface AttachmentService {

	Attachment getById(int id);

	void addToProject(Attachment attachment, Project project);

	void addToTeam(Attachment attachment, Team team);

	void update(Attachment attachment);

	void delete(Attachment attachment);

	List<Attachment> getTeamAttachments(Team team);

	List<Attachment> getProjectAttachments(Project project);
}