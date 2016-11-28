package com.netcracker.ca.model.dto;

import java.util.List;

import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;

public class TeamUsersDto {

	private Team team;
	
	private List<User> curators;
	
	private List<Student> students;
	
	public TeamUsersDto() {}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<User> getCurators() {
		return curators;
	}

	public void setCurators(List<User> curators) {
		this.curators = curators;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
}
