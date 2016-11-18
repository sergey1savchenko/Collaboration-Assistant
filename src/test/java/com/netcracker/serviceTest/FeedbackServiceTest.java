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

import com.netcracker.ca.dao.FeedbackDao;
import com.netcracker.ca.model.Feedback;
import com.netcracker.ca.service.impl.FeedbackServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceTest {
	
	@Mock
    private FeedbackDao feedbackDao;
	private Feedback feedback;
	private int id;
	
	@InjectMocks
    private FeedbackServiceImpl feedbackService = new FeedbackServiceImpl();
	
	@Test
    public void getAllTest() {
		List<Feedback> feedbacks = feedbackService.getAll();
		verify(feedbackDao).getAll();
		assertNotNull(feedbacks);
	}
	
	@Test
    public void getByIdTest() {
		feedbackService.getById(id);
		verify(feedbackDao).getById(id);
	}
	
	@Test
    public void addTest() throws SQLException {
		feedbackService.add(feedback);
		verify(feedbackDao).add(feedback);
	}
	
	@Test
    public void updateTest() throws SQLException {
		feedbackService.update(feedback);
		verify(feedbackDao).update(feedback);
	}
	
	@Test
    public void deleteTest() throws SQLException {
		feedbackService.delete(id);
		verify(feedbackDao).delete(id);
	}
	
}
