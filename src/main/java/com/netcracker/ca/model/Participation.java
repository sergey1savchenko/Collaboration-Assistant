package com.netcracker.ca.model;

import java.time.LocalDateTime;

public class Participation {

	private int id;

	private Student student;

	private Project project;

	private ProjectStatus status;

	private String comment;

	private LocalDateTime assigned;

	private Team team;

	public Participation() {
	}

	public Participation(Student student, Project project, ProjectStatus status, String comment, LocalDateTime assigned,
			Team team) {
		this.student = student;
		this.project = project;
		this.status = status;
		this.comment = comment;
		this.assigned = assigned;
		this.team = team;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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
		Participation other = (Participation) obj;
		if (id != other.id)
			return false;
		return true;
	}

}