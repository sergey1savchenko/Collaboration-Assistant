package com.netcracker.serviceTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import com.netcracker.dao.*;
import com.netcracker.model.*;
import com.netcracker.service.impl.*;

@RunWith(MockitoJUnitRunner.class)
public class AttachmentServiceTest {
	
	@Mock
    private AttachmentDAO attachmentDAO;
	private Attachment attachment;
	private Project project;
	private Team team;
	private int id;
	
	@InjectMocks
    private AttachmentServiceImpl attachmentService = new AttachmentServiceImpl();
	
	@Test
    public void getByIdTest() {
		attachmentService.getById(id);
		verify(attachmentDAO).getById(id);
	}
	
	@Test
    public void addToProjectTest() {
		attachmentService.addToProject(attachment, project);
		verify(attachmentDAO).addToProject(attachment, project);
	}
	
	@Test
    public void addToTeamTest() {
		attachmentService.addToTeam(attachment, team);
		verify(attachmentDAO).addToTeam(attachment, team);
	}
	
	@Test
    public void updateTest() {
		attachmentService.update(attachment);
		verify(attachmentDAO).update(attachment);
	}
	
	@Test
    public void deleteTest() {
		attachmentService.delete(attachment);
		verify(attachmentDAO).delete(attachment);
	}
	
	@Test
    public void getTeamAttachmentsTest() {
		List<Attachment> attachments = attachmentService.getTeamAttachments(team);
		verify(attachmentDAO).getTeamAttachments(team);
		assertNotNull(attachments);
	}
	
	@Test
    public void getProjectAttachmentsTest() {
		List<Attachment> attachments = attachmentService.getProjectAttachments(project);
		verify(attachmentDAO).getProjectAttachments(project);
		assertNotNull(attachments);
	}
	
}
