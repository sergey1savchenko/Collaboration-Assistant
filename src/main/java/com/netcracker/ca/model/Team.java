package com.netcracker.ca.model;

public class Team {

	private int id;
	private String title;
	private Project project;

	public Team() {
	}
	
	public Team(int id) {
		this.id = id;
	}

	public Team(int id, String title, Project project) {
		super();
		this.id = id;
		this.title = title;
		this.project = project;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Team");
		return builder
			.append(" [id=").append(id)
			.append(", title=").append(title)
			.append(", project.id=").append(project != null ? project.getId(): 0)
			.append("]").toString();
	}
}