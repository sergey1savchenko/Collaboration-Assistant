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

import com.netcracker.ca.dao.ProjectEvaluationDao;
import com.netcracker.ca.model.ProjectEvaluation;
import com.netcracker.ca.service.impl.ProjectEvaluationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProjectEvaluationServiceTest {
	
	@Mock
    private ProjectEvaluationDao projectEvaluationDao;
	private ProjectEvaluation projectEvaluation;
	private int id;
	
	@InjectMocks
    private ProjectEvaluationServiceImpl projectEvaluationService = new ProjectEvaluationServiceImpl();
	
	@Test
    public void addTest() throws SQLException {
		projectEvaluationService.add(projectEvaluation);
		verify(projectEvaluationDao).add(projectEvaluation);
	}
	
	@Test
    public void updateTest() throws SQLException {
		projectEvaluationService.update(projectEvaluation);
		verify(projectEvaluationDao).update(projectEvaluation);
	}
	
	@Test
    public void deleteTest() throws SQLException {
		projectEvaluationService.delete(id);
		verify(projectEvaluationDao).delete(id);
	}
	
	@Test
    public void getEvaluationsOfTeamTest() {
		List<ProjectEvaluation> projectEvaluations = projectEvaluationService.getEvaluationsOfTeam(id);
		verify(projectEvaluationDao).getEvaluationsOfTeam(id);
		assertNotNull(projectEvaluations);
	}
	
	@Test
    public void getEvaluationsOfProjectTest() {
		List<ProjectEvaluation> projectEvaluations = projectEvaluationService.getEvaluationsOfProject(id);
		verify(projectEvaluationDao).getEvaluationsOfProject(id);
		assertNotNull(projectEvaluations);
	}
	
}
