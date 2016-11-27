package com.netcracker.ca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.netcracker.ca.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.MeetingEvaluationDao;

/**
 * Created by Oleksandr on 12.11.2016.
 */
@Repository
public class MeetingEvaluationDaoImpl implements MeetingEvaluationDao {

	private static String SQL_INSERT_ME = "INSERT INTO meeting_evaluations (curator_id, attendance_id, marktype_id, int_value, text_value) VALUES (?, ?, ?, ?, ?)";
	private static String SQL_UPDATE_ME = "UPDATE meeting_evaluations SET curator_id=?, attendance_id=?, marktype_id=?, int_value=?, text_value=? WHERE id=?";
	private static String SQL_DELETE_ME = "DELETE FROM meeting_evaluations WHERE id=?";
	private static String SQL_SELECT_ALL_ME = "SELECT meeting_evaluations.id AS meid, marktypes.id AS mtid, marktypes.title AS mttitle, has_int, has_text, int_value, text_value, st.id AS sid, st.first_name AS sfn, st.last_name AS sln,\n"
			+ "cur.id AS cid, cur.first_name AS cfn, cur.last_name AS cln, meetings.id AS mid, meetings.title AS mtitle,\n" + "attendance.is_present\n"
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
	public void delete(Integer id) {
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
			me.setId(rs.getInt("meid"));
			me.setIntValue(rs.getInt("int_value"));
			me.setTextValue(rs.getString("text_value"));
			Student student = new Student();
			student.setId(rs.getInt("sid"));
			student.setFirstName(rs.getString("sfn"));
			student.setLastName(rs.getString("sln"));
			me.setStudent(student);
			User curator = new User();
			curator.setId(rs.getInt("cid"));
			curator.setFirstName(rs.getString("cfn"));
			curator.setLastName(rs.getString("cln"));
			me.setCurator(curator);
			MarkType markType = new MarkType();
			markType.setId(rs.getInt("mtid"));
			markType.setTitle(rs.getString("mttitle"));
			markType.setHasInt(rs.getBoolean("has_int"));
			markType.setHasText(rs.getBoolean("has_text"));
			me.setMarktype(markType);
			Meeting meeting = new Meeting();
			meeting.setId(rs.getInt("mid"));
			meeting.setTitle(rs.getString("mtitle"));
			me.setMeeting(meeting);
			me.setAttendance(rs.getBoolean("is_present"));
			return me;
		}
	}

	@Override
	public MeetingEvaluation getById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

}
