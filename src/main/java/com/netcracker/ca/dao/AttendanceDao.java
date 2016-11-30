package com.netcracker.ca.dao;

import com.netcracker.ca.model.Attendance;

public interface AttendanceDao {
	
	Attendance getByStudentAndMeeting(int studentId, int meetingId);
	
	void add(Attendance attendance, int studentId, int meetingId);
	
	void update(Attendance attendance);
	
	void delete(int id);
	
}
