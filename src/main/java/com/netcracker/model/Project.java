package com.netcracker.model;

import java.sql.Timestamp;

public class Project {

	private int id;
	private String title;
	private String description;
	private Timestamp startDate;
	private Timestamp endDate;
	private University university;

	public Project() {
	}

	public Project(int id) {
		this.id = id;
	}

	public Project(int id, String title, String description, Timestamp startDate, Timestamp endDate,
			University university) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.university = university;
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

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public int getUniversityId() {
		return university.getId();
	}

	public void setUniversityId(int id) {
		if (this.university == null)
			this.university = new University();
		this.university.setId(id);
	}

	public String getUniversityTitle() {
		return university.getTitle();
	}

	public void setUniversityTitle(String title) {
		if (this.university == null)
			this.university = new University();
		this.university.setTitle(title);
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
		Project other = (Project) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", description=" + description + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", university=" + university + "]";
	}
}