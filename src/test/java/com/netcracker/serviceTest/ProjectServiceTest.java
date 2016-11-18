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

import com.netcracker.ca.dao.ProjectDao;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.service.impl.ProjectServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {
	
	@Mock
    private ProjectDao projectDao;
	private Project project;
	private int id;
	
	@InjectMocks
    private ProjectServiceImpl projectService = new ProjectServiceImpl();
	
	@Test
    public void getAllTest() {
		List<Project> projects = projectService.getAll();
		verify(projectDao).getAll();
		assertNotNull(projects);
	}
	
	@Test
	public void getByIdTest(){
		projectService.getById(id);
		verify(projectDao).getById(id);
	}
	
	@Test
	public void addTest() throws SQLException{
		projectService.add(project);
		verify(projectDao).add(project);
	}
	
	@Test
	public void updateTest() throws SQLException{
		projectService.update(project);
		verify(projectDao).update(project);
	}
	
	@Test
	public void deleteTest() throws SQLException{
		projectService.delete(id);
		verify(projectDao).delete(id);
	}
	
}
