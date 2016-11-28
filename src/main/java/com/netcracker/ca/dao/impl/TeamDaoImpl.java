package com.netcracker.ca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import com.netcracker.ca.model.University;

@Repository
public class TeamDaoImpl implements TeamDao {

	private static final String SQL_SELECT_TEAMS = "SELECT id, title, project_id FROM teams";
	private static final String SQL_SELECT_TEAM_BY_ID = SQL_SELECT_TEAMS + " WHERE id = ?";
	private static final String SQL_INSERT_TEAM = "INSERT INTO teams (title, project_id) VALUES (?, ?)";
	private static final String SQL_UPDATE_TEAM = "UPDATE teams SET title = ?, project_id = ? WHERE id = ?";
	private static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE id = ?";
	private static final String SQL_SELECT_ALL_TEAMS_OF_PROJECT = SQL_SELECT_TEAMS + " WHERE project_id = ?";
	private static final String SQL_SELECT_TEAM_BY_TITLE = SQL_SELECT_TEAMS + " WHERE title = ?";
	private static final String SQL_SELECT_TEAM_AND_PROJECT = "SELECT t.id AS t_id, t.title AS t_title, p.id AS p_id, p.title as p_title, p.description, p.start_date, "
			+ "p.end_date, un.id AS un_id, un.title as un_title FROM teams AS t INNER JOIN projects AS p ON t.project_id=p.id "
			+ "INNER JOIN universities AS un ON p.university_id=un.id";
	private static final String SQL_SELECT_CURRENT_FOR_CURATOR = SQL_SELECT_TEAM_AND_PROJECT
			+ " INNER JOIN curators_in_project cp ON p.id=cp.project_id " + "WHERE cp.user_id=? AND p.end_date>?";
	private static final String SQL_SELECT_CURRENT_FOR_STUDENT = SQL_SELECT_TEAM_AND_PROJECT
			+ " INNER JOIN students_in_project AS sp ON p.id=sp.project_id "
			+ "INNER JOIN application_forms AS af ON sp.app_form_id=af.id WHERE af.user_id=? AND p.end_date>?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Team> getAll() {
		return jdbcTemplate.query(SQL_SELECT_TEAMS, new TeamMapper());
	}

	@Override
	public Team getById(Integer id) {
		List<Team> teams = jdbcTemplate.query(SQL_SELECT_TEAM_BY_ID, new TeamMapper(), id);
		return teams.isEmpty() ? null : teams.get(0);
	}

	@Override
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

	@Override
	public void update(Team team) {
		jdbcTemplate.update(SQL_UPDATE_TEAM, team.getTitle(), team.getProject().getId(), team.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(SQL_DELETE_TEAM, id);
	}

	@Override
	public List<Team> getByProject(int projectId) {
		return jdbcTemplate.query(SQL_SELECT_ALL_TEAMS_OF_PROJECT, new TeamMapper(), projectId);
	}

	@Override
	public Team getByTitle(String title) {
		return jdbcTemplate.queryForObject(SQL_SELECT_TEAM_BY_TITLE, new TeamMapper(), title);
	}

	@Override
	public Team getCurrentForCurator(int curatorId) {
		List<Team> teams = jdbcTemplate.query(SQL_SELECT_CURRENT_FOR_CURATOR, new TeamProjectMapper(), curatorId,
				new Date());
		return teams.isEmpty() ? null : teams.get(0);
	}

	@Override
	public Team getCurrentForStudent(int studentId) {
		List<Team> teams = jdbcTemplate.query(SQL_SELECT_CURRENT_FOR_STUDENT, new TeamProjectMapper(), studentId,
				new Date());
		return teams.isEmpty() ? null : teams.get(0);
	}

	private static class TeamMapper implements RowMapper<Team> {

		public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
			Team team = new Team();
			team.setId(rs.getInt("id"));
			team.setTitle(rs.getString("title"));
			team.setProject(new Project(rs.getInt("project_id")));
			return team;
		}
	}

	private static class TeamProjectMapper implements RowMapper<Team> {

		@Override
		public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
			Team team = new Team();
			team.setId(rs.getInt("t_id"));
			team.setTitle(rs.getString("t_title"));
			Project project = new Project(rs.getInt("p_id"));
			project.setTitle(rs.getString("p_title"));
			project.setDescription(rs.getString("description"));
			project.setStartDate(rs.getTimestamp("start_date"));
			project.setEndDate(rs.getTimestamp("end_date"));
			University uni = new University(rs.getInt("un_id"));
			uni.setTitle(rs.getString("un_title"));
			project.setUniversity(uni);
			team.setProject(project);
			return team;
		}

	}

}