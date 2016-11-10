package loki.ca.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import loki.ca.model.Project;
import loki.ca.model.Team;
import loki.ca.orm.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TeamDAOImpl implements TeamDAO{

	private static final String SQL_SELECT_ALL_TEAMS = "SELECT id, title, project_id FROM teams";
	private static final String SQL_SELECT_TEAM_BY_ID = "SELECT id, title, project_id FROM teams WHERE id=?";
	private static final String SQL_INSERT_TEAM = "INSERT INTO teams VALUES (?, ?, ?)";
	private static final String SQL_UPDATE_TEAM = "UPDATE teams SET id=?, title=?, project_id=? WHERE id=?";
	private static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE id=?";
	private static final String SQL_SELECT_ALL_TEAMS_OF_PROJECT = SQL_SELECT_ALL_TEAMS + " WHERE project_id=?";
	private static final String SQL_GET_TEAM_BY_TITLE = SQL_SELECT_ALL_TEAMS + " WHERE title=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Team> getAllTeams() {
		return jdbcTemplate.query(SQL_SELECT_ALL_TEAMS, new TeamMapper());
	}

	public Team getTeamById(int id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_TEAM_BY_ID, new TeamMapper(), id);
	}

	public void addTeam(Team team) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_TEAM);
		ps.setInt(1, team.getId());
		ps.setString(2, team.getTitle());
		ps.setInt(3, team.getProject_id());
		ps.executeUpdate();
		ps.close();
	}

	public void updateTeam(Team team) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_TEAM);
		ps.setInt(1, team.getId());
		ps.setString(2, team.getTitle());
		ps.setInt(3, team.getProject_id());
		ps.executeUpdate();
		ps.close();
		
	}

	public void deleteTeam(int id) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_TEAM);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		
	}

	public List<Team> getAllTeamsOfProject(Project project) {
		return jdbcTemplate.query(SQL_SELECT_ALL_TEAMS_OF_PROJECT, new TeamMapper(), project.getId());
	}

	public Team getTeamByTitle(String title) {
		return jdbcTemplate.queryForObject(SQL_GET_TEAM_BY_TITLE, new TeamMapper(), title);
	}
	
	public static void main(String[] args) throws SQLException{
		TeamDAOImpl tdi = new TeamDAOImpl();
		Team test = new Team(1, 1, "Test");
		tdi.addTeam(test);
		
	}
	
	
}
