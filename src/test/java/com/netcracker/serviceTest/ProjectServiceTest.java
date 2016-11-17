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
public class ProjectServiceTest {
	
	@Mock
    private ProjectDAO projectDAO;
	private Project project;
	private int id;
	
	@InjectMocks
    private ProjectServiceImpl projectService = new ProjectServiceImpl();
	
	@Test
    public void getAllTest() {
		List<Project> projects = projectService.getAll();
		verify(projectDAO).getAll();
		assertNotNull(projects);
	}
	
	@Test
	public void getByIdTest(){
		projectService.getById(id);
		verify(projectDAO).getById(id);
	}
	
	@Test
	public void addTest() throws SQLException{
		projectService.add(project);
		verify(projectDAO).add(project);
	}
	
	@Test
	public void updateTest() throws SQLException{
		projectService.update(project);
		verify(projectDAO).update(project);
	}
	
	@Test
	public void deleteTest() throws SQLException{
		projectService.delete(id);
		verify(projectDAO).delete(id);
	}
	
}
