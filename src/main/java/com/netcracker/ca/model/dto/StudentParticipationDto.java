package com.netcracker.ca.model.dto;

import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.Student;

public class StudentParticipationDto {

	private Student student;
	
	private Participation participation;
	
	public StudentParticipationDto() {}
	
	public StudentParticipationDto(Student student, Participation participation) {
		this.student = student;
		this.participation = participation;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Participation getParticipation() {
		return participation;
	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}
	
}
