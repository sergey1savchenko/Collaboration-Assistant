package com.netcracker.ca.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.CuratorDao;
import com.netcracker.ca.model.Role;
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
	private static final String SQL_SELECT_CURATORS_BY_MEETING = SQL_SELECT_CURATORS + " WHERE cp.team_id=(SELECT id FROM meetings WHERE team_id=?)";
	private static final String SQL_SELECT_CURATOR_ROLE = "SELECT id FROM roles WHERE role='CURATOR'";
	private static String SQL_SELECT_FREE_CURATORS = "SELECT u.id AS u_id, u.email, u.first_name, u.second_name, u.last_name "
			+ "FROM users AS u INNER JOIN user_roles AS ur ON u.id=ur.user_id LEFT JOIN curators_in_project AS cp ON u.id=cp.user_id "
			+ "LEFT JOIN projects AS pr ON cp.project_id=pr.id WHERE ur.role_id=%s AND "
			+ "(pr.end_date IS NULL OR pr.end_date<?) GROUP BY u.id, ur.role_id";
	private static Role curatorRole;
	
	@PostConstruct
	private void init() {
		curatorRole = new Role(jdbcTemplate.queryForObject(SQL_SELECT_CURATOR_ROLE, Integer.class), "CURATOR");
		SQL_SELECT_FREE_CURATORS = String.format(SQL_SELECT_FREE_CURATORS, curatorRole.getId());
	}

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
	public List<User> getFreeCurators() {
		return jdbcTemplate.query(SQL_SELECT_FREE_CURATORS, new CuratorRowMapper(), new Date());
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

	private static class CuratorRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("u_id"));
			user.setEmail(rs.getString("email"));
			user.setFirstName(rs.getString("first_name"));
			user.setSecondName(rs.getString("second_name"));
			user.setLastName(rs.getString("last_name"));
			user.setRole(curatorRole);
			return user;
		}

	}

}
