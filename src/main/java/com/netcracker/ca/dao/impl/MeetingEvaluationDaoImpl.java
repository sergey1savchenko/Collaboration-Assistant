package com.netcracker.ca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.MeetingEvaluationDao;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MeetingEvaluation;

/**
 * Created by Oleksandr on 12.11.2016.
 */
@Repository
public class MeetingEvaluationDaoImpl implements MeetingEvaluationDao {

	private static String SQL_SELECT_ATTENDANCE_BY_STUDENT_AND_MEETING = "SELECT a.id FROM application_forms AS af INNER JOIN students_in_project AS p "
			+ "ON af.id=p.app_form_id INNER JOIN attendance AS a ON p.id=a.student_in_project_id WHERE af.user_id=? AND a.meeting_id=?";
	private static String SQL_SELECT_CURATORSHIP_BY_CURATOR_AND_MEETING = "SELECT id FROM curators_in_project WHERE user_id=? AND "
			+ "project_id=(SELECT project_id FROM meetings WHERE id=?)";
	private static String SQL_INSERT_ME = String.format("INSERT INTO meeting_evaluations (int_value, text_value, marktype_id, attendance_id, curator_id) VALUES "
			+ "(?, ?, ?, (%s), (%s))", SQL_SELECT_ATTENDANCE_BY_STUDENT_AND_MEETING, SQL_SELECT_CURATORSHIP_BY_CURATOR_AND_MEETING);
	private static String SQL_INSERT_ALL_ME = "INSERT INTO meeting_evaluations (int_value, text_value, marktype_id, attendance_id, curator_id) VALUES "
			+ "(?, ?, ?, ?, ?)";
	private static String SQL_UPDATE_ME = "UPDATE meeting_evaluations SET int_value=?, text_value=? WHERE id=?";
	private static String SQL_DELETE_ME = "DELETE FROM meeting_evaluations WHERE id=?";
	private static String SQL_SELECT_ALL_ME = "SELECT me.id AS me_id, mt.id AS mt_id, mt.title, mt.has_int, mt.has_text, me.int_value, me.text_value \n" 
			+ "FROM meeting_evaluations AS me INNER JOIN marktypes AS mt ON me.marktype_id = mt.id\n";
	private static String SQL_SELECT_ALL_ME_BY_ID = SQL_SELECT_ALL_ME + " WHERE me.id=?";
	private static String SQL_SELECT_ALL_ME_BY_STUDENT_AND_MEETING_AND_CURATOR = SQL_SELECT_ALL_ME + " INNER JOIN attendance AS at ON me.attendance_id=at.id "
			+ "INNER JOIN students_in_project AS p ON at.student_in_project_id=p.id INNER JOIN application_forms AS af ON p.app_form_id=af.id "
			+ "INNER JOIN curators_in_project AS cp ON me.curator_id=cp.id WHERE af.user_id=? AND at.meeting_id=? AND cp.user_id=?";
	private static String SQL_SELECT_ALL_ME_BY_STUDENT_AND_MEETING_PER_CURATOR = "SELECT me.id AS me_id, mt.id AS mt_id, mt.title, mt.has_int, mt.has_text, "
			+ "me.int_value, me.text_value, cp.user_id AS c_id FROM meeting_evaluations AS me "
			+ "INNER JOIN marktypes AS mt ON me.marktype_id = mt.id INNER JOIN attendance AS at ON me.attendance_id=at.id "
			+ "INNER JOIN students_in_project AS p ON at.student_in_project_id=p.id INNER JOIN application_forms AS af ON p.app_form_id=af.id "
			+ "INNER JOIN curators_in_project AS cp ON me.curator_id=cp.id "
			+ "WHERE af.user_id=? AND at.meeting_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public MeetingEvaluation getById(int id) {
		List<MeetingEvaluation> list = jdbcTemplate.query(SQL_SELECT_ALL_ME_BY_ID, new MeetingEvaluationMapper(), id);
		return list.isEmpty() ? null: list.get(0);
	}

	@Override
	public void add(final MeetingEvaluation me, final int studentId, final int meetingId, final int curatorId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_ME, new String[] { "id" });
				ps.setInt(1, me.getIntValue());
				ps.setString(2, me.getTextValue());
				ps.setInt(3, me.getMarktype().getId());
				ps.setInt(4, studentId);
				ps.setInt(5, meetingId);
				ps.setInt(6, curatorId);
				ps.setInt(7, meetingId);
				return ps;
			}
		}, keyHolder);
		me.setId(keyHolder.getKey().intValue());
	}
	

	@Override
	public void addAll(final List<MeetingEvaluation> mes, int studentId, int meetingId, int curatorId) {
		final int attendanceId = jdbcTemplate.queryForObject(SQL_SELECT_ATTENDANCE_BY_STUDENT_AND_MEETING,
				new Object[] { studentId, meetingId }, Integer.class);
		final int curatorshipId = jdbcTemplate.queryForObject(SQL_SELECT_CURATORSHIP_BY_CURATOR_AND_MEETING,
				new Object[] { curatorId, meetingId }, Integer.class);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		for(final MeetingEvaluation me: mes) {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(SQL_INSERT_ALL_ME, new String[] { "id" });
					ps.setInt(1, me.getIntValue());
					ps.setString(2, me.getTextValue());
					ps.setInt(3, me.getMarktype().getId());
					ps.setInt(4, attendanceId);
					ps.setInt(5, curatorshipId);
					return ps;
				}
			}, keyHolder);
			me.setId(keyHolder.getKey().intValue());
		}
	}

	@Override
	public void update(MeetingEvaluation me) {
		jdbcTemplate.update(SQL_UPDATE_ME, me.getIntValue(), me.getTextValue(), me.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_ME, id);
	}
	
	@Override
	public List<MeetingEvaluation> getByStudentAndMeetingAndCurator(int studentId, int meetingId, int curatorId) {
		return jdbcTemplate.query(SQL_SELECT_ALL_ME_BY_STUDENT_AND_MEETING_AND_CURATOR, new MeetingEvaluationMapper(), 
				studentId, meetingId, curatorId);
	}
	
	@Override
	public Map<Integer, List<MeetingEvaluation>> getByStudentAndMeetingPerCurator(int studentId, int meetingId) {
		return jdbcTemplate.query(SQL_SELECT_ALL_ME_BY_STUDENT_AND_MEETING_PER_CURATOR, new ResultSetExtractor<Map<Integer, List<MeetingEvaluation>>>() {

			@Override
			public Map<Integer, List<MeetingEvaluation>> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				Map<Integer, List<MeetingEvaluation>> result = new HashMap<>();
				Map<Integer, MarkType> markTypes = new HashMap<>();
				while(rs.next()) {
					MeetingEvaluation me = new MeetingEvaluation();
					me.setId(rs.getInt("me_id"));
					me.setIntValue(rs.getInt("int_value"));
					me.setTextValue(rs.getString("text_value"));
					int markTypeId = rs.getInt("mt_id");
					MarkType markType = markTypes.get(markTypeId);
					if(markType == null) {
						markType = new MarkType();
						markType.setId(markTypeId);
						markType.setTitle(rs.getString("title"));
						markType.setHasInt(rs.getBoolean("has_int"));
						markType.setHasText(rs.getBoolean("has_text"));
						markTypes.put(markTypeId, markType);
					}
					me.setMarktype(markType);
					int curatorId = rs.getInt("c_id");
					List<MeetingEvaluation> evals = result.get(curatorId);
					if(evals == null) {
						evals = new ArrayList<MeetingEvaluation>();
						result.put(curatorId, evals);
					}
					evals.add(me);
				}
				return result;
			}
			
			
		}, studentId, meetingId);
	}

	private static class MeetingEvaluationMapper implements RowMapper<MeetingEvaluation> {
		public MeetingEvaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
			MeetingEvaluation me = new MeetingEvaluation();
			me.setId(rs.getInt("me_id"));
			me.setIntValue(rs.getInt("int_value"));
			me.setTextValue(rs.getString("text_value"));
			MarkType markType = new MarkType();
			markType.setId(rs.getInt("mt_id"));
			markType.setTitle(rs.getString("title"));
			markType.setHasInt(rs.getBoolean("has_int"));
			markType.setHasText(rs.getBoolean("has_text"));
			me.setMarktype(markType);
			return me;
		}
	}
}
