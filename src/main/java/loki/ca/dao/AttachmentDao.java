package loki.ca.dao;

import java.util.List;

import loki.ca.model.Attachment;
import loki.ca.model.Project;
import loki.ca.model.Team;

public interface AttachmentDao {

	Attachment getAttachmentById(int id);
	
	void addAttachmentToProject(Attachment attachment, Project project);
	
	void addAttachmentToTeam(Attachment attachment, Team team);
	
	void updateAttachment(Attachment attachment);
	
	void deleteAttachment(Attachment attachment);
	
	List<Attachment> getTeamAttachments(Team team);
	
	List<Attachment> getProjectAttachments(Project project);
}
