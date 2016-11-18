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

import com.netcracker.ca.dao.TeamDao;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

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

	public void add(final Team team) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_TEAM, new String[] { "id" });
				ps.setString(1, team.getTitle());
				ps.setInt(2, team.getProject().getId());
				return ps;
			}
		}, keyHolder);
		team.setId(keyHolder.getKey().intValue());
	}

	public void update(Team team) {
		jdbcTemplate.update(SQL_UPDATE_TEAM, team.getTitle(), team.getProject().getId(), team.getId());
	}

	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_TEAM, id);
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
			team.setTitle(rs.getString("team_title"));
			Project project = new Project();
			project.setId(rs.getInt("project_id"));
			project.setTitle(rs.getString("project_title"));
			team.setProject(project);
			return team;
		}
	}
}