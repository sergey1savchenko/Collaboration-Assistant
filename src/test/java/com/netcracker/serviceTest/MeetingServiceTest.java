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
public class MeetingServiceTest {
	
	@Mock
    private MeetingDao meetingDao;
	private Meeting meeting;
	private int id;
	
	@InjectMocks
    private MeetingServiceImpl meetingService = new MeetingServiceImpl();
	
	@Test
    public void addTest() throws SQLException {
		meetingService.add(meeting);
		verify(meetingDao).add(meeting);
	}
	
	@Test
    public void updateTest() throws SQLException {
		meetingService.update(meeting);
		verify(meetingDao).update(meeting);
	}
	
	@Test
    public void deleteTest() throws SQLException {
		meetingService.delete(id);
		verify(meetingDao).delete(id);
	}
	
	@Test
    public void getAllTeamMeetingsTest() {
		List<Meeting> meetings = meetingService.getAllTeamMeetings(id);
		verify(meetingDao).getAllTeamMeetings(id);
		assertNotNull(meetings);
	}
	
	@Test
    public void getAllProjectMeetingsTest() {
		List<Meeting> meetings = meetingService.getAllProjectMeetings(id);
		verify(meetingDao).getAllProjectMeetings(id);
		assertNotNull(meetings);
	}
	
}
