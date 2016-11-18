package com.netcracker.serviceTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.netcracker.ca.dao.AttachmentDao;
import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.impl.AttachmentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AttachmentServiceTest {
	
	@Mock
    private AttachmentDao attachmentDao;
	private Attachment attachment;
	private Project project;
	private Team team;
	private int id;
	
	@InjectMocks
    private AttachmentServiceImpl attachmentService = new AttachmentServiceImpl();
	
	@Test
    public void getByIdTest() {
		attachmentService.getById(id);
		verify(attachmentDao).getById(id);
	}
	
	@Test
    public void addToProjectTest() {
		attachmentService.addToProject(attachment, project.getId());
		verify(attachmentDao).addToProject(attachment, project.getId());
	}
	
	@Test
    public void addToTeamTest() {
		attachmentService.addToTeam(attachment, project.getId(), team.getId());
		verify(attachmentDao).addToTeam(attachment, project.getId(), team.getId());
	}
	
	@Test
    public void updateTest() {
		attachmentService.update(attachment);
		verify(attachmentDao).update(attachment);
	}
	
	@Test
    public void deleteTest() {
		attachmentService.delete(attachment.getId());
		verify(attachmentDao).delete(attachment.getId());
	}
	
	@Test
    public void getTeamAttachmentsTest() {
		List<Attachment> attachments = attachmentService.getTeamAttachments(team.getId());
		verify(attachmentDao).getTeamAttachments(team.getId());
		assertNotNull(attachments);
	}
	
	@Test
    public void getProjectAttachmentsTest() {
		List<Attachment> attachments = attachmentService.getProjectAttachments(project.getId());
		verify(attachmentDao).getProjectAttachments(project.getId());
		assertNotNull(attachments);
	}
	
}
