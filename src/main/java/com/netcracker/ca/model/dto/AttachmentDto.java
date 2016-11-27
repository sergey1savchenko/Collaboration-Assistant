package com.netcracker.ca.model.dto;

import java.io.InputStream;

public class AttachmentDto {

	private InputStream input;
	
	private String name;
	
	private String text;
	
	private String mimeType;
	
	private int projectId;
	
	private int teamId;

	public AttachmentDto(InputStream input, String name, String text, String mimeType, int id, boolean isTeamAttachment) {
		super();
		this.input = input;
		this.name = name;
		this.text = text;
		this.mimeType = mimeType;
		if(isTeamAttachment)
			this.teamId = id;
		else 
			this.projectId = id;	
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
}
