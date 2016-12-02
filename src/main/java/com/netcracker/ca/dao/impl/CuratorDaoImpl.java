package com.netcracker.ca.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.CuratorDao;
import com.netcracker.ca.model.User;

@Repository
public class CuratorDaoImpl implements CuratorDao {

	private static final String SQL_INSERT_CURATOR = "INSERT INTO curators_in_project (user_id, project_id, team_id) SELECT ?, ?, ? "
			+ "WHERE NOT EXISTS (SELECT * FROM curators_in_project WHERE user_id=? AND project_id=?)";
	private static final String SQL_DELETE_CURATOR = "DELETE FROM curators_in_project WHERE user_id=? AND project_id=?";
	private static final String SQL_SELECT_CURATORS = "SELECT u.id AS u_id, u.email, u.first_name, u.second_name, u.last_name "
			+ "FROM users AS u INNER JOIN curators_in_project AS cp ON u.id=cp.user_id";
	private static final String SQL_SELECT_CURATORS_BY_PROJECT = SQL_SELECT_CURATORS + " WHERE cp.project_id=?";
	private static final String SQL_SELECT_CURATORS_BY_TEAM = SQL_SELECT_CURATORS + " WHERE cp.team_id=?";
	private static final String SQL_SELECT_CURATORS_BY_PROJECT_IN_TEAMS = "SELECT u.id AS u_id, u.email, u.first_name, u.second_name, u.last_name, cp.team_id AS t_id "
			+ "FROM users AS u INNER JOIN curators_in_project AS cp ON u.id=cp.user_id WHERE cp.project_id=?";
	private static final String SQL_SELECT_CURATORS_BY_MEETING = SQL_SELECT_CURATORS + " INNER JOIN meetings AS m ON cp.team_id=m.team_id";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(int curatorId, int projectId, int teamId) {
		jdbcTemplate.update(SQL_INSERT_CURATOR, curatorId, projectId, teamId, curatorId, projectId);
	}

	@Override
	public void delete(int curatorId, int projectId) {
		jdbcTemplate.update(SQL_DELETE_CURATOR, curatorId, projectId);
	}

	@Override
	public List<User> getByTeam(int teamId) {
		return jdbcTemplate.query(SQL_SELECT_CURATORS_BY_TEAM, new CuratorRowMapper(), teamId);
	}

	@Override
	public List<User> getByProject(int projectId) {
		return jdbcTemplate.query(SQL_SELECT_CURATORS_BY_PROJECT, new CuratorRowMapper(), projectId);
	}
	
	@Override
	public List<User> getByMeeting(int meetingId) {
		return jdbcTemplate.query(SQL_SELECT_CURATORS_BY_MEETING, new CuratorRowMapper(), meetingId);
	}

	@Override
	public Map<Integer, List<User>> getByProjectInTeams(int projectId) {
		return jdbcTemplate.query(SQL_SELECT_CURATORS_BY_PROJECT_IN_TEAMS,
				new ResultSetExtractor<Map<Integer, List<User>>>() {
					@Override
					public Map<Integer, List<User>> extractData(ResultSet rs) throws SQLException, DataAccessException {
						Map<Integer, List<User>> result = new HashMap<>();
						while (rs.next()) {
							User curator = new User();
							curator.setId(rs.getInt("u_id"));
							curator.setEmail(rs.getString("email"));
							curator.setFirstName(rs.getString("first_name"));
							curator.setSecondName(rs.getString("second_name"));
							curator.setLastName(rs.getString("last_name"));
							int teamId = rs.getInt("t_id");
							List<User> curators = result.get(teamId);
							if (curators == null) {
								curators = new ArrayList<User>();
								result.put(teamId, curators);
							}
							curators.add(curator);
						}
						return result;
					}
				}, projectId);

	}

	private static class CuratorRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("u_id"));
			user.setEmail(rs.getString("email"));
			user.setFirstName(rs.getString("first_name"));
			user.setSecondName(rs.getString("second_name"));
			user.setLastName(rs.getString("last_name"));
			return user;
		}

	}
}
