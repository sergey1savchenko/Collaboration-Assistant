package com.netcracker.ca.model;

public class Attachment {

	private int id;

	private String text;

	private String link;

	private String mimeType;

	public Attachment() {
	}

	public Attachment(String text, String link, String mimeType) {
		super();
		this.text = text;
		this.link = link;
		this.mimeType = mimeType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attachment other = (Attachment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", text=" + text + ", link=" + link + ", mimeType=" + mimeType + "]";
	}
}