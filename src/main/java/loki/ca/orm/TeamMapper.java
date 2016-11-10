package loki.ca.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import loki.ca.model.Team;

public class TeamMapper implements RowMapper<Team> {

	public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setProject_id(rs.getInt("project_id"));
		team.setTitle(rs.getString("title"));
		return team;
	}

}
