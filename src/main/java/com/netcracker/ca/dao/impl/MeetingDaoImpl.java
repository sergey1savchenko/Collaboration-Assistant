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

import com.netcracker.ca.dao.MeetingDao;
import com.netcracker.ca.model.Meeting;

/**
 * Created by Oleksandr on 10.11.2016.
 */

@Repository
public class MeetingDaoImpl implements MeetingDao {

	private static String SQL_INSERT_PROJECT_MEETING = "INSERT INTO meetings (address, title, datetime, project_id) VALUES (?, ?, ?, ?)";
	private static String SQL_INSERT_TEAM_MEETING = "INSERT INTO meetings (address, title, datetime, project_id, team_id) VALUES (?, ?, ?, (SELECT project_id FROM teams WHERE id=?), ?)";
	private static String SQL_UPDATE_MEETING = "UPDATE meetings SET address=?, title=?, datetime=? WHERE id=?";
	private static String SQL_DELETE_MEETING = "DELETE FROM meetings WHERE id=?";
	private static String SQL_SELECT_MEETING = "SELECT id, title, address, datetime FROM meetings";
	private static String SQL_SELECT_MEETING_BY_ID = SQL_SELECT_MEETING + " WHERE id=?";
	private static String SQL_SELECT_TEAM_MEETINGS = SQL_SELECT_MEETING + " WHERE team_id=?";
	private static String SQL_SELECT_PROJECT_MEETINGS = SQL_SELECT_MEETING + " WHERE project_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Meeting getById(int id) {
		List<Meeting> meetings = jdbcTemplate.query(SQL_SELECT_MEETING_BY_ID, new MeetingMapper(), id);
		return meetings.isEmpty() ? null : meetings.get(0);
	}

	@Override
	public void addToProject(final Meeting meeting, final int projectId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_PROJECT_MEETING, new String[] { "id" });
				ps.setString(1, meeting.getAddress());
				ps.setString(2, meeting.getTitle());
				ps.setTimestamp(3, meeting.getDatetime());
				ps.setInt(4, projectId);

				return ps;
			}
		}, keyHolder);
		meeting.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void addToTeam(final Meeting meeting, final int teamId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_TEAM_MEETING, new String[] { "id" });
				ps.setString(1, meeting.getAddress());
				ps.setString(2, meeting.getTitle());
				ps.setTimestamp(3, meeting.getDatetime());
				ps.setInt(4, teamId);
				ps.setInt(5, teamId);

				return ps;
			}
		}, keyHolder);
		meeting.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Meeting meeting) {
		jdbcTemplate.update(SQL_UPDATE_MEETING, meeting.getAddress(), meeting.getTitle(), meeting.getDatetime(),
				meeting.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_MEETING, id);
	}

	@Override
	public List<Meeting> getAllTeamMeetings(int id) {
		return jdbcTemplate.query(SQL_SELECT_TEAM_MEETINGS, new MeetingMapper(), id);
	}

	@Override
	public List<Meeting> getAllProjectMeetings(int id) {
		return jdbcTemplate.query(SQL_SELECT_PROJECT_MEETINGS, new MeetingMapper(), id);
	}

	private static class MeetingMapper implements RowMapper<Meeting> {
		public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
			Meeting meeting = new Meeting();
			meeting.setId(rs.getInt("id"));
			meeting.setAddress(rs.getString("address"));
			meeting.setTitle(rs.getString("title"));
			meeting.setDatetime(rs.getTimestamp("datetime"));
			return meeting;
		}
	}

}
