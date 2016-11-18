package com.netcracker.serviceTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.netcracker.ca.dao.TeamDao;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.impl.TeamServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {
	
	@Mock
	private TeamDao teamDao;
	private Team team;
	private Project project;
	private int id;
	
	@InjectMocks
    private TeamServiceImpl teamService = new TeamServiceImpl();
	
	@Test
    public void getAllTest() {
		List<Team> teams = teamService.getAll();
		verify(teamDao).getAll();
		assertNotNull(teams);
	}
	
	@Test
	public void getByIdTest(){
		teamService.getById(id);
		verify(teamDao).getById(id);
	}
	
	@Test
    public void addTest() throws SQLException {
		teamService.add(team);
		verify(teamDao).add(team);
	}
	
	@Test
    public void updateTest() throws SQLException {
		teamService.update(team);
		verify(teamDao).update(team);
	}
	
	@Test
    public void deleteTest() throws SQLException {
		teamService.delete(id);
		verify(teamDao).delete(id);
	}
	
	@Test
    public void getByProjectTest() {
		List<Team> teams = teamService.getByProject(project);
		verify(teamDao).getByProject(project);
		assertNotNull(teams);
	}
	
	@Test
    public void getByTitleTest() {
		teamService.getByTitle("title");
		verify(teamDao).getByTitle("title");
	}
	
}
