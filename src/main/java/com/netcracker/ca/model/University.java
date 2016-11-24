package com.netcracker.ca.model;

public class University {

	private int id;
	private String title;
	private String description;
	private String city;

	public University() {
	}

	public University(int id) {
		this.id = id;
	}

	public University(int id, String title, String description, String city) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.city = city;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
		University other = (University) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Student");
		return builder
			.append(" [id=").append(id)
			.append(", title=").append(title)
			.append(", description=").append(description)
			.append(", city=").append(city)
			.append("]").toString();
	}
}