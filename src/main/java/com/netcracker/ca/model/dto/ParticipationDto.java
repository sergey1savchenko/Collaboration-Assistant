package com.netcracker.ca.model.dto;

public class ParticipationDto {

	private int id;

	private int studentId;

	private int teamId;

	public ParticipationDto() {}
	
	public ParticipationDto(int id) {
		this.id = id;
	}
	
	public ParticipationDto(int id, int studentId, int teamId) {
		this.id = id;
		this.studentId = studentId;
		this.teamId = teamId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
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
		ParticipationDto other = (ParticipationDto) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
