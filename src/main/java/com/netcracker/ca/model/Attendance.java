package com.netcracker.ca.model;

public class Attendance {
	
	private int id;

	private boolean isPresent;
	
	public Attendance() {}
	
	public Attendance(int id) {
		this.id = id;
	}
	
	public Attendance(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
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
		Attendance other = (Attendance) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Attendance");
		return builder
			.append(" [id=").append(id)
			.append(", isPresent=").append(isPresent)
			.append("]").toString();
	}
	
}
