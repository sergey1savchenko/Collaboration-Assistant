package com.netcracker.ca.model;

public class InterviewStatus {

	private int id;
	private String description;

	public InterviewStatus() {
	}

	public InterviewStatus(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		InterviewStatus other = (InterviewStatus) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("InterviewStatus");
		return builder
			.append(" [id=").append(id)
			.append(", description=").append(description)
			.append("]").toString();
	}
}