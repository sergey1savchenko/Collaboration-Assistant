package com.netcracker.ca.model;

/**
 * Created by Oleksandr on 09.11.2016.
 */
public class MeetingEvaluation {
	private int id;
	private int intValue;
	private String textValue;
	private Student student;
	private User curator;
	private MarkType marktype;
	private Meeting meeting;
	private int attendanceId;
	private boolean attendance;

	public MeetingEvaluation() {
	}

	public MeetingEvaluation(int id, int intValue, String textValue, Student student, User curator, MarkType marktype,
			Meeting meeting, int attendanceId, boolean attendance) {
		this.id = id;
		this.intValue = intValue;
		this.textValue = textValue;
		this.student = student;
		this.curator = curator;
		this.marktype = marktype;
		this.meeting = meeting;
		this.attendanceId = attendanceId;
		this.attendance = attendance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public User getCurator() {
		return curator;
	}

	public void setCurator(User curator) {
		this.curator = curator;
	}

	public MarkType getMarktype() {
		return marktype;
	}

	public void setMarktype(MarkType marktype) {
		this.marktype = marktype;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	@Override
	public String toString() {
		return "MeetingEvaluation{" + "id=" + id + ", intValue=" + intValue + ", textValue='" + textValue + '\''
				+ ", student=" + student.getFirstName() + " " + student.getLastName() + ", curator="
				+ curator.getFirstName() + " " + curator.getLastName() + ", marktype=" + marktype.getTitle()
				+ ", meeting=" + meeting.getTitle() + ", attendance=" + attendance + '}';
	}
}
