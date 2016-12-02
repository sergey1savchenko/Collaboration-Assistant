package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;

public interface TeamService {

	Team getById(int id);
	
	Team getByIdWithProject(int id);

	void add(Team team, int projectId);

	void update(Team team);

	void delete(int id);

	List<Team> getAll();

	List<Team> getByProject(int projectId);

	Team getByTitle(String title);

	Team getByMeeting(int meetingId);
	
	Team getForAttachment(int attachmentId);

	Team getCurrentForStudent(int studentId);

	Team getCurrentForCurator(int curatorId);

	List<Team> generateTeams(List<Student> students, List<Team> teams);
}