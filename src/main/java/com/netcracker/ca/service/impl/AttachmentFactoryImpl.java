package com.netcracker.ca.service.impl;

import org.springframework.stereotype.Component;

import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.AttachmentFactory;

@Component
public class AttachmentFactoryImpl implements AttachmentFactory {

	@Override
	public Attachment build(String name, String text, String mimeType, int id, boolean isTeamAttachment) {
		Attachment att = new Attachment();
		att.setText(text);
		att.setMimeType(mimeType);
		StringBuilder link = new StringBuilder();
		link.append(isTeamAttachment ? "/team/": "/project/").append(id).append('/').append(name);
		att.setLink(link.toString());
		return att;
	}

}
