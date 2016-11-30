package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.AttendanceDao;
import com.netcracker.ca.dao.MeetingDao;
import com.netcracker.ca.model.Attendance;
import com.netcracker.ca.model.Meeting;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.service.MeetingService;
import com.netcracker.ca.service.StudentService;

/**
 * Created by Oleksandr on 12.11.2016.
 */

@Service
@Transactional (readOnly = true)
public class MeetingServiceImpl implements MeetingService{
	
	private static boolean DEFAULT_IS_PRESENT = true;

    @Autowired
    private MeetingDao meetingDao;
    
    @Autowired
    private AttendanceDao attendanceDao;
    
    @Autowired 
    private StudentService studentService;
    

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addToProject(Meeting meeting, int projectId) {
        meetingDao.addToProject(meeting, projectId);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addToTeam(Meeting meeting, int teamId) {
        meetingDao.addToTeam(meeting, teamId);
        for(Student student: studentService.getByTeam(teamId)) {
        	attendanceDao.add(new Attendance(DEFAULT_IS_PRESENT), student.getId(), meeting.getId());
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(Meeting meeting) {
        meetingDao.update(meeting);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) {
        meetingDao.delete(id);
    }

    @Override
    public List<Meeting> getAllTeamMeetings(int id) {
        return meetingDao.getAllTeamMeetings(id);
    }

    @Override
    public List<Meeting> getAllProjectMeetings(int id) {
        return meetingDao.getAllProjectMeetings(id);
    }

	@Override
	public void setAttendance(int meetingId, int studentId, boolean isPresent) {
		Attendance att = attendanceDao.getByStudentAndMeeting(studentId, meetingId);
		att.setPresent(isPresent);
		attendanceDao.update(att);
	}
	
	public void getAttendance(int meetingId, int studentId) {
		
	}
}
