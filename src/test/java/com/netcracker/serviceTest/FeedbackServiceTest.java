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
public class FeedbackServiceTest {
	
	@Mock
    private FeedbackDAO feedbackDAO;
	private Feedback feedback;
	private int id;
	
	@InjectMocks
    private FeedbackServiceImpl feedbackService = new FeedbackServiceImpl();
	
	@Test
    public void getAllTest() {
		List<Feedback> feedbacks = feedbackService.getAll();
		verify(feedbackDAO).getAll();
		assertNotNull(feedbacks);
	}
	
	@Test
    public void getByIdTest() {
		feedbackService.getById(id);
		verify(feedbackDAO).getById(id);
	}
	
	@Test
    public void addTest() throws SQLException {
		feedbackService.add(feedback);
		verify(feedbackDAO).add(feedback);
	}
	
	@Test
    public void updateTest() throws SQLException {
		feedbackService.update(feedback);
		verify(feedbackDAO).update(feedback);
	}
	
	@Test
    public void deleteTest() throws SQLException {
		feedbackService.delete(id);
		verify(feedbackDAO).delete(id);
	}
	
}
