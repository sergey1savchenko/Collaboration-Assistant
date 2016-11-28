package com.netcracker.ca.service;

import com.netcracker.ca.model.Attachment;

public interface AttachmentFactory {

	Attachment build(String name, String text, String mimeType, int id, boolean isTeamAttachment);
	
}
