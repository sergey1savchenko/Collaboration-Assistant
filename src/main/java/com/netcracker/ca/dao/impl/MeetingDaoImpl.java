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
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;

/**
 * Created by Oleksandr on 10.11.2016.
 */

@Repository
public class MeetingDaoImpl implements MeetingDao {

	private static String SQL_INSERT_MEETING = "INSERT INTO meetings (address, title, datetime, project_id, team_id) VALUES (?, ?, ?, ?, ?)";
	private static String SQL_UPDATE_MEETING = "UPDATE meetings SET address=?, title=?, datetime=?, project_id=?, team_id=? WHERE id=?";
	private static String SQL_DELETE_MEETING = "DELETE FROM meetings WHERE id=?";
	private static String SQL_SELECT_ALL_MEETINGS = "SELECT meetings.id, meetings.title, datetime, projects.id, projects.title, teams.id, teams.title"
			+ "FROM meetings INNER JOIN projects ON meetings.project_id = projects.id"
			+ "INNER JOIN teams ON meetings.team_id = teams.id";
	private static String SQL_SELECT_TEAM_MEETINGS = SQL_SELECT_ALL_MEETINGS + " WHERE teams.id=?";
	private static String SQL_SELECT_PROJECT_MEETINGS = SQL_SELECT_ALL_MEETINGS + " WHERE projects.id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final Meeting meeting) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_MEETING, new String[] { "id" });
				ps.setString(1, meeting.getAddress());
				ps.setString(2, meeting.getTitle());
				ps.setTimestamp(3, meeting.getDatetime());
				ps.setInt(4, meeting.getProject().getId());
				ps.setInt(5, meeting.getTeam().getId());
				return ps;
			}
		}, keyHolder);
		meeting.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Meeting meeting) {
		jdbcTemplate.update(SQL_UPDATE_MEETING, meeting.getAddress(), meeting.getTitle(), meeting.getDatetime(),
				meeting.getProject().getId(), meeting.getTeam().getId(), meeting.getId());
	}

	@Override
	public void delete(Integer id) {
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
			Project project = new Project();
			project.setId(rs.getInt("id"));
			project.setTitle(rs.getString("title"));
			meeting.setProject(project);
			meeting.setTeam(new Team(rs.getInt("id"), rs.getString("title"), project));
			return meeting;
		}
	}

	@Override
	public Meeting getById(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}
}
