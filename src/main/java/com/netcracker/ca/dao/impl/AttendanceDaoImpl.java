package com.netcracker.ca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.AttendanceDao;
import com.netcracker.ca.model.Attendance;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
	private static final String SQL_SELECT_ATTENDANCE = "SELECT a.id AS a_id, a.is_present FROM attendance AS a";
	private static final String SQL_SELECT_ATTENDANCE_BY_STUDENT_AND_MEETING = SQL_SELECT_ATTENDANCE + " INNER JOIN students_in_project AS p ON a.student_in_project_id=p.id "
			+ "INNER JOIN application_forms AS af ON p.app_form_id=af.id WHERE af.user_id=? AND a.meeting_id=?";
	private static final String SQL_INSERT_ATTENDANCE = "INSERT INTO attendance (meeting_id, student_in_project_id, is_present) VALUES (?, (SELECT p.id FROM application_forms AS af "
			+ "INNER JOIN students_in_project AS p ON p.app_form_id=af.id WHERE af.user_id=? AND p.project_id=(SELECT project_id FROM meetings WHERE id=?)), ?)";
	private static final String SQL_UPDATE_ATTENDANCE = "UPDATE attendance SET is_present=? WHERE id=?";
	private static final String SQL_DELETE_ATTENDANCE = "DELETE FROM attendance WHERE id=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Attendance getByStudentAndMeeting(int studentId, int meetingId) {
		List<Attendance> list = jdbcTemplate.query(SQL_SELECT_ATTENDANCE_BY_STUDENT_AND_MEETING, new AttendanceRowMapper(), studentId, meetingId);
		return list.isEmpty()? null: list.get(0);
	}
	
	@Override
	public void add(final Attendance attendance, final int studentId, final int meetingId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_ATTENDANCE, new String[] { "id" });
				ps.setInt(1, meetingId);
				ps.setInt(2, studentId);
				ps.setInt(3, meetingId);
				ps.setBoolean(4, attendance.isPresent());
				return ps;
			}
		}, keyHolder);
		attendance.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Attendance attendance) {
		jdbcTemplate.update(SQL_UPDATE_ATTENDANCE, attendance.isPresent(), attendance.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_ATTENDANCE, id);
	}
	
	private static class AttendanceRowMapper implements RowMapper<Attendance> {

		@Override
		public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
			Attendance a = new Attendance(rs.getInt("id"));
			a.setPresent(rs.getBoolean("is_present"));
			return a;
		}
		
	}

}
