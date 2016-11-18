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

import com.netcracker.ca.dao.MeetingEvaluationDao;
import com.netcracker.ca.model.MeetingEvaluation;

/**
 * Created by Oleksandr on 12.11.2016.
 */
@Repository
public class MeetingEvaluationDaoImpl implements MeetingEvaluationDao {

	private static String SQL_INSERT_ME = "INSERT INTO meeting_evaluations (curator_id, attendance_id, marktype_id, int_value, text_value) VALUES (?, ?, ?, ?, ?)";
	private static String SQL_UPDATE_ME = "UPDATE meeting_evaluations SET curator_id=?, attendance_id=?, marktype_id=?, int_value=?, text_value=? WHERE id=?";
	private static String SQL_DELETE_ME = "DELETE FROM meeting_evaluations WHERE id=?";
	private static String SQL_SELECT_ALL_ME = "SELECT meeting_evaluations.id, marktypes.id, marktypes.title, has_int, has_text, int_value, text_value, st.id, st.first_name, st.last_name,\n"
			+ "cur.id, cur.first_name, cur.last_name, meetings.id, meetings.title,\n" + "attendance.is_present\n"
			+ "FROM meeting_evaluations INNER JOIN attendance ON meeting_evaluations.attendance_id = attendance.id\n"
			+ "INNER JOIN students_in_project ON attendance.student_in_project_id = students_in_project.id\n"
			+ "INNER JOIN application_forms ON students_in_project.app_form_id = application_forms.id\n"
			+ "INNER JOIN users AS st ON application_forms.user_id = st.id\n"
			+ "INNER JOIN curators_in_project ON meeting_evaluations.curator_id = curators_in_project.id\n"
			+ "INNER JOIN users AS cur ON curators_in_project.user_id = cur.id\n"
			+ "INNER JOIN marktypes ON meeting_evaluations.marktype_id = marktypes.id\n"
			+ "INNER JOIN meetings ON attendance.meeting_id = meetings.id\n";
	private static String SQL_GET_STUDENTS_EVALUATION = SQL_SELECT_ALL_ME + " WHERE st.id=?";
	private static String SQL_GET_EVALUATION_BY_MEETING = SQL_SELECT_ALL_ME + " WHERE meetings.id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final MeetingEvaluation me) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_ME, new String[] { "id" });
				ps.setInt(1, me.getCurator().getId());
				ps.setInt(2, me.getAttendanceId());
				ps.setInt(3, me.getMarktype().getId());
				ps.setInt(4, me.getIntValue());
				ps.setString(5, me.getTextValue());
				return ps;
			}
		}, keyHolder);
		me.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(MeetingEvaluation me) {
		jdbcTemplate.update(SQL_UPDATE_ME, me.getCurator().getId(), me.getAttendanceId(), me.getMarktype().getId(),
				me.getIntValue(), me.getTextValue(), me.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_ME, id);
	}

	@Override
	public List<MeetingEvaluation> getStudentsEvaluation(int id) {
		return jdbcTemplate.query(SQL_GET_STUDENTS_EVALUATION, new MeetingEvaluationMapper(), id);
	}

	@Override
	public List<MeetingEvaluation> getEvaluationByMeeting(int id) {
		return jdbcTemplate.query(SQL_GET_EVALUATION_BY_MEETING, new MeetingEvaluationMapper(), id);
	}

	private static class MeetingEvaluationMapper implements RowMapper<MeetingEvaluation> {
		public MeetingEvaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
			MeetingEvaluation me = new MeetingEvaluation();
			me.setId(rs.getInt("id"));
			me.setIntValue(rs.getInt("int_value"));
			me.setTextValue(rs.getString("text_value"));
			//TODO Add aliases to query
			/*me.setStudentId(rs.getInt("id"));
			me.setStudentFirstName(rs.getString("first_name"));
			me.setStudentLastName(rs.getString("last_name"));
			me.setCuratorId(rs.getInt("id"));
			me.setCuratorFirstName(rs.getString("first_name"));
			me.setCuratorLastName(rs.getString("last_name"));
			me.setMarkTypeId(rs.getInt("id"));
			me.setMarkTypeTitle(rs.getString("title"));
			me.setMarkTypeHasInt(rs.getBoolean("has_int"));
			me.setMarkTypeHasText(rs.getBoolean("has_text"));
			me.setMeetingId(rs.getInt("id"));
			me.setMeetingTitle(rs.getString("title"));*/
			me.setAttendanceId(rs.getInt("attendance_id"));
			me.setAttendance(rs.getBoolean("is_present"));
			return me;
		}
	}

}
