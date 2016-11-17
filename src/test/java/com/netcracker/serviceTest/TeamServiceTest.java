package com.netcracker.serviceTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.List;
import com.netcracker.dao.*;
import com.netcracker.model.*;
import com.netcracker.service.impl.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {
	
	@Mock
	private TeamDAO teamDAO;
	private Team team;
	private Project project;
	private int id;
	
	@InjectMocks
    private TeamServiceImpl teamService = new TeamServiceImpl();
	
	@Test
    public void getAllTest() {
		List<Team> teams = teamService.getAll();
		verify(teamDAO).getAll();
		assertNotNull(teams);
	}
	
	@Test
	public void getByIdTest(){
		teamService.getById(id);
		verify(teamDAO).getById(id);
	}
	
	@Test
    public void addTest() throws SQLException {
		teamService.add(team);
		verify(teamDAO).add(team);
	}
	
	@Test
    public void updateTest() throws SQLException {
		teamService.update(team);
		verify(teamDAO).update(team);
	}
	
	@Test
    public void deleteTest() throws SQLException {
		teamService.delete(id);
		verify(teamDAO).delete(id);
	}
	
	@Test
    public void getByProjectTest() {
		List<Team> teams = teamService.getByProject(project);
		verify(teamDAO).getByProject(project);
		assertNotNull(teams);
	}
	
	@Test
    public void getByTitleTest() {
		teamService.getByTitle("title");
		verify(teamDAO).getByTitle("title");
	}
	
}
