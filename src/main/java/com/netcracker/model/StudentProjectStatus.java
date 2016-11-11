package com.netcracker.model;

import java.time.LocalDateTime;

public class StudentProjectStatus {

	private int id;

	private String comment;

	private LocalDateTime assigned;

	private StudentProjectStatusType type;

	public StudentProjectStatus() {
	}

	public StudentProjectStatus(String comment, LocalDateTime assigned, StudentProjectStatusType type) {
		super();
		this.comment = comment;
		this.assigned = assigned;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getAssigned() {
		return assigned;
	}

	public void setAssigned(LocalDateTime assigned) {
		this.assigned = assigned;
	}

	public StudentProjectStatusType getType() {
		return type;
	}

	public void setType(StudentProjectStatusType type) {
		this.type = type;
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
		StudentProjectStatus other = (StudentProjectStatus) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentProjectStatus [id=" + id + ", comment=" + comment + ", assigned=" + assigned + ", type=" + type
				+ "]";
	}
}