package com.netcracker.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netcracker.dao.TeamDAO;
import com.netcracker.model.Project;
import com.netcracker.model.Team;

@Repository
public class TeamDAOImpl implements TeamDAO {

	private static final String SQL_SELECT_ALL_TEAMS = "SELECT teams.id, teams.title as team_title, teams.project_id, projects.title as project_title FROM teams INNER JOIN projects ON teams.project_id = projects.id";
	private static final String SQL_SELECT_TEAM_BY_ID = SQL_SELECT_ALL_TEAMS + " WHERE id = ?";
	private static final String SQL_INSERT_TEAM = "INSERT INTO teams (title, project_id) VALUES (?, ?)";
	private static final String SQL_UPDATE_TEAM = "UPDATE teams SET title = ?, project_id = ? WHERE id = ?";
	private static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE id = ?";
	private static final String SQL_SELECT_ALL_TEAMS_OF_PROJECT = SQL_SELECT_ALL_TEAMS + " WHERE teams.project_id = ?";
	private static final String SQL_GET_TEAM_BY_TITLE = SQL_SELECT_ALL_TEAMS + " WHERE teams.title = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Team> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL_TEAMS, new TeamMapper());
	}

	public Team getById(int id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_TEAM_BY_ID, new TeamMapper(), id);
	}

	public void add(Team team) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_TEAM);
		ps.setString(1, team.getTitle());
		ps.setInt(2, team.getProjectId());
		ps.executeUpdate();
		ps.close();
	}

	public void update(Team team) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_TEAM);
		ps.setString(1, team.getTitle());
		ps.setInt(2, team.getProjectId());
		ps.setInt(3, team.getId());
		ps.executeUpdate();
		ps.close();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_TEAM);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
	}

	public List<Team> getByProject(Project project) {
		return jdbcTemplate.query(SQL_SELECT_ALL_TEAMS_OF_PROJECT, new TeamMapper(), project.getId());
	}

	public Team getByTitle(String title) {
		return jdbcTemplate.queryForObject(SQL_GET_TEAM_BY_TITLE, new TeamMapper(), title);
	}

	private static class TeamMapper implements RowMapper<Team> {

		public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
			Team team = new Team();
			team.setId(rs.getInt("id"));
			team.setProjectId(rs.getInt("project_id"));
			team.setTitle(rs.getString("team_title"));
			team.setProjectTitle(rs.getString("project_title"));
			return team;
		}
	}
}