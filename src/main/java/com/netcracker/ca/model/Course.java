package com.netcracker.ca.model;

public class Course {

	private int id;
	private String title;

	public Course() {
	}

	public Course(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Course(int id) {
		this.id = id;
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
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Course");
		return builder
			.append(" [id=").append(id)
			.append(", title=").append(title)
			.append("]").toString();
	}
}