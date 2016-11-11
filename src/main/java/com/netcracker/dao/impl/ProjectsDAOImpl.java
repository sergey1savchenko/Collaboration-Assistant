package com.netcracker.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netcracker.dao.ProjectDAO;
import com.netcracker.model.Project;

@Repository
public class ProjectsDAOImpl implements ProjectDAO {

	private static final String SQL_SELECT_ALL_PROJECTS = "SELECT projects.id, projects.title as project_title, projects.description, projects.start_date, projects.end_date, projects.university_id, universities.title as university_title FROM (projects INNER JOIN universities ON projects.university_id = universities.id)";
	private static final String SQL_SELECT_PROJECT_BY_ID = SQL_SELECT_ALL_PROJECTS + " WHERE projects.id = ?";
	private static final String SQL_INSERT_PROJECT = "INSERT INTO projects (title, description, start_date, end_date, university_id) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_PROJECT = "UPDATE projects SET title = ?, description = ?, start_date = ?, end_date = ?, university_id = ? WHERE projects.id = ?";
	private static final String SQL_DELETE_PROJECT = "DELETE FROM projects WHERE projects.id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Project> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL_PROJECTS, new ProjectMapper());
	}

	@Override
	public Project getById(int id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_PROJECT_BY_ID, new ProjectMapper(), id);
	}

	@Override
	public void add(Project project) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_PROJECT);
		ps.setString(1, project.getTitle());
		ps.setString(2, project.getDescription());
		ps.setTimestamp(3, project.getStartDate());
		ps.setTimestamp(4, project.getEndDate());
		ps.setInt(5, project.getUniversityId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void update(Project project) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_PROJECT);
		ps.setString(1, project.getTitle());
		ps.setString(2, project.getDescription());
		ps.setTimestamp(3, project.getStartDate());
		ps.setTimestamp(4, project.getEndDate());
		ps.setInt(5, project.getUniversityId());
		ps.setInt(6, project.getId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_PROJECT);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
	}

	private static class ProjectMapper implements RowMapper<Project> {

		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project project = new Project();
			project.setId(rs.getInt("id"));
			project.setTitle(rs.getString("project_title"));
			project.setDescription(rs.getString("description"));
			project.setStartDate(rs.getTimestamp("start_date"));
			project.setEndDate(rs.getTimestamp("end_date"));
			project.setUniversityId(rs.getInt("university_id"));
			project.setUniversityTitle(rs.getString("university_title"));
			return project;
		}
	}
}