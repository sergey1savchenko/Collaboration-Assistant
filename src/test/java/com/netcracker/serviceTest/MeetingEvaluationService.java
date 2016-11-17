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
public class MeetingEvaluationService {
	
	@Mock
    private MeetingEvaluationDao meetingEvaluationDao;
	private MeetingEvaluation meetingEvaluation;
	private int id;
	
	@InjectMocks
    private MeetingEvaluationServiceImpl meetingEvaluationService = new MeetingEvaluationServiceImpl();
	
	@Test
    public void addTest() throws SQLException {
		meetingEvaluationService.add(meetingEvaluation);
		verify(meetingEvaluationDao).add(meetingEvaluation);
	}
	
	@Test
    public void updateTest() throws SQLException {
		meetingEvaluationService.update(meetingEvaluation);
		verify(meetingEvaluationDao).update(meetingEvaluation);
	}
	
	@Test
    public void deleteTest() throws SQLException{
		meetingEvaluationService.delete(id);
		verify(meetingEvaluationDao).delete(id);
	}
	
	@Test
    public void getStudentsEvaluationTest() {
		List<MeetingEvaluation> meetingEvaluations = meetingEvaluationService.getStudentsEvaluation(id);
		verify(meetingEvaluationDao).getStudentsEvaluation(id);
		assertNotNull(meetingEvaluations);
	}
	
	@Test
    public void getEvaluationByMeetingTest() {
		List<MeetingEvaluation> meetingEvaluations = meetingEvaluationService.getEvaluationByMeeting(id);
		verify(meetingEvaluationDao).getEvaluationByMeeting(id);
		assertNotNull(meetingEvaluations);
	}
	
}
