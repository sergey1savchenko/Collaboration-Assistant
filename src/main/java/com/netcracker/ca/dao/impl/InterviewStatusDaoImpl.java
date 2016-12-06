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

import com.netcracker.ca.dao.InterviewStatusDao;
import com.netcracker.ca.model.InterviewStatus;

@Repository
public class InterviewStatusDaoImpl implements InterviewStatusDao {

	private static String SQL_SELECT_INTERVIEW_STATUS = "SELECT * FROM student_for_hr_status_types";
	private static String SQL_SELECT_INTERVIEW_STATUS_BY_ID = SQL_SELECT_INTERVIEW_STATUS + " WHERE id=?";
	private static String SQL_SELECT_INTERVIEW_STATUS_BY_DESC = SQL_SELECT_INTERVIEW_STATUS + " WHERE description=?";
	private static String SQL_INSERT_INTERVIEW_STATUS = "INSERT INTO student_for_hr_status_types (description) VALUES (?)";
	private static String SQL_UPDATE_INTERVIEW_STATUS = "UPDATE student_for_hr_status_types SET description=? WHERE id=?";
	private static String SQL_DELETE_INTERVIEW_STATUS = "DELETE FROM student_for_hr_status_types WHERE id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public InterviewStatus getById(Integer id) {
		List<InterviewStatus> list = jdbcTemplate.query(SQL_SELECT_INTERVIEW_STATUS_BY_ID,
				new InterviewStatusRowMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public InterviewStatus getByDesc(String desc) {
		List<InterviewStatus> list = jdbcTemplate.query(SQL_SELECT_INTERVIEW_STATUS_BY_DESC,
				new InterviewStatusRowMapper(), desc);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void add(final InterviewStatus status) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_INTERVIEW_STATUS, new String[] { "id" });
				ps.setString(1, status.getDescription());
				return ps;
			}
		}, keyHolder);
		status.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(InterviewStatus status) {
		jdbcTemplate.update(SQL_UPDATE_INTERVIEW_STATUS, status.getDescription(), status.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(SQL_DELETE_INTERVIEW_STATUS, id);
	}

	private static class InterviewStatusRowMapper implements RowMapper<InterviewStatus> {

		@Override
		public InterviewStatus mapRow(ResultSet rs, int n) throws SQLException {
			InterviewStatus ps = new InterviewStatus();
			ps.setId(rs.getInt("id"));
			ps.setDescription(rs.getString("description"));
			return ps;
		}
	}
}