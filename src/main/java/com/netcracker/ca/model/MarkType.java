package com.netcracker.ca.model;

public class MarkType {

	private int id;

	private String title;

	private boolean hasText;

	private boolean hasInt;

	public MarkType() {
	}

	public MarkType(String title, boolean hasText, boolean hasInt) {
		this.title = title;
		this.hasText = hasText;
		this.hasInt = hasInt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean hasText() {
		return hasText;
	}

	public void setHasText(boolean hasText) {
		this.hasText = hasText;
	}

	public boolean hasInt() {
		return hasInt;
	}

	public void setHasInt(boolean hasInt) {
		this.hasInt = hasInt;
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
		MarkType other = (MarkType) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("MarkType");
		return builder
			.append(" [id=").append(id)
			.append(", title=").append(title)
			.append(", hasText=").append(hasText)
			.append(", hasInt=").append(hasInt)
			.append("]").toString();
	}
}