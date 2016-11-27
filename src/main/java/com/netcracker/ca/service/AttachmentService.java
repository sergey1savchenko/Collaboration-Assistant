package com.netcracker.ca.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.dto.AttachmentDto;

public interface AttachmentService {

	Attachment add(AttachmentDto attDto);
	
	Resource getAsResource(int id);

	void delete(int id);

	List<Attachment> getTeamAttachments(int teamId);

	List<Attachment> getProjectAttachments(int projectId);
}