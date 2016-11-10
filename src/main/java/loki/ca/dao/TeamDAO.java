package loki.ca.dao;

import java.sql.SQLException;
import java.util.List;

import loki.ca.model.Team;
import loki.ca.model.Project;

public interface TeamDAO {
	
	public List<Team> getAllTeams();

	public Team getTeamById(int id);

	public void addTeam(Team team) throws SQLException;

	public void updateTeam(Team team) throws SQLException;

	public void deleteTeam(int id) throws SQLException;

	public List<Team> getAllTeamsOfProject(Project project);
	
	public Team getTeamByTitle(String title);
	
	

}
