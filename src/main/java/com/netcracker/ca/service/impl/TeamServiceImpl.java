package com.netcracker.ca.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.TeamDao;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.TeamService;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDao teamDao;

	@Override
	public List<Team> getAll() {
		return teamDao.getAll();
	}

	@Override
	public Team getById(int id) {
		return teamDao.getById(id);
	}

	@Override
	public void add(Team team, int projectId) {
		team.setProject(new Project(projectId));
		teamDao.add(team);
	}

	@Override
	public void update(Team team) {
		teamDao.update(team);
	}

	@Override
	public void delete(int id) {
		teamDao.delete(id);
	}

	@Override
	public List<Team> getByProject(int projectId) {
		return teamDao.getByProject(projectId);
	}

	@Override
	public Team getByTitle(String title) {
		return teamDao.getByTitle(title);
	}

	@Override
	public Team getByMeeting(int meetingId) {
		return teamDao.getByMeeting(meetingId);
	}
	
	@Override
	public Team getForAttachment(int attachmentId) {
		return teamDao.getForAttachment(attachmentId);
	}

	@Override
	public Team getCurrentForStudent(int studentId) {
		return teamDao.getCurrentForStudent(studentId);
	}

	@Override
	public Team getCurrentForCurator(int curatorId) {
		return teamDao.getCurrentForCurator(curatorId);
	}

	@Override
	public List<Team> generateTeams(List<Student> std, List<Team> tm) {
		List<Team> teams = new ArrayList<Team>();
		List<Student> students = std;
		for (Team team : teams) {
			students.addAll(team.getStudents());
		}

		students.sort(new Comparator<Student>() {

			@Override
			public int compare(Student s1, Student s2) {
				int value;
				if (s1.getCourse().getTitle() > s2.getCourse().getTitle())
					value = 1;
				else if (s1.getCourse().getTitle() < s2.getCourse().getTitle())
					value = -1;
				else
					value = 0;
				return value;
			}
		});

		int count = students.size();
		while (count != 0) {
			for (int i = 0; i < teams.size(); i++) {
				teams.get(i).getStudents().add(students.get(count));
				count--;
			}
		}
		return teams;
	}

}