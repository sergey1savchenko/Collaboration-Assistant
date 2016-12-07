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
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.MeetingService;
import com.netcracker.ca.service.NotificationService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.StudentService;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.utils.ServiceException;

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
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private NotificationService notificationService;
    
	@Override
	public Meeting getById(int id) {
		return meetingDao.getById(id);
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addToProject(Meeting meeting, int projectId) {
    	if(meetingDao.getByTitleForProject(meeting.getTitle(), projectId) != null)
    		throw new ServiceException("Meeting title must be unique");
        meetingDao.addToProject(meeting, projectId);
        //notificationService.onMeetingCreated(meeting);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addToTeam(Meeting meeting, int teamId) {
    	if(meetingDao.getByTitleForTeam(meeting.getTitle(), teamId) != null)
    		throw new ServiceException("Meeting title must be unique");
    	meetingDao.addToTeam(meeting, teamId);
        for(Student student: studentService.getByTeamAndStatus(teamId, "Involved")) {
        	attendanceDao.add(new Attendance(DEFAULT_IS_PRESENT), student.getId(), meeting.getId());
        }
        //notificationService.onMeetingCreated(meeting);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(Meeting meeting) {
    	Team meetingTeam = teamService.getByMeeting(meeting.getId());
    	if(meetingTeam != null) {
    		Meeting byTitleForTeam = meetingDao.getByTitleForTeam(meeting.getTitle(), meetingTeam.getId());
    		if(byTitleForTeam != null && !byTitleForTeam.equals(meeting))
    			throw new ServiceException("Meeting title must be unique");
    	}
		Meeting byTitleForProject = meetingDao.getByTitleForProject(meeting.getTitle(), projectService.getForMeeting(meeting.getId()).getId());
		if(byTitleForProject != null && !byTitleForProject.equals(meeting))
			throw new ServiceException("Meeting title must be unique");
    	
        meetingDao.update(meeting);
        //notificationService.onMeetingEdited(meeting);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) {
        meetingDao.delete(id);
        Meeting meeting = meetingDao.getById(id);
    	//if(meeting != null)
    	//	 notificationService.onMeetingDeleted(meeting);
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
	public boolean belongsToTeam(int meetingId, int teamId) {
		return teamService.getByMeeting(meetingId).getId() == teamId;
	}
	
	@Override
	public boolean belongsToProject(int meetingId, int projectId) {
		return projectService.getForMeeting(meetingId).getId() == projectId;
	}

	@Override
	public void setAttendance(int meetingId, int studentId, boolean isPresent) {
		Attendance att = attendanceDao.getByStudentAndMeeting(studentId, meetingId);
		att.setPresent(isPresent);
		attendanceDao.update(att);
	}
	
	@Override
	public boolean getAttendance(int meetingId, int studentId) {
		return attendanceDao.getByStudentAndMeeting(studentId, meetingId).isPresent();
	}

}
