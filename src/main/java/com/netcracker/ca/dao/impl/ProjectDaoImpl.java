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

import com.netcracker.ca.dao.ProjectDao;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.University;

@Repository
public class ProjectDaoImpl implements ProjectDao {

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
		List<Project> projects = jdbcTemplate.query(SQL_SELECT_PROJECT_BY_ID, new ProjectMapper(), id);
		return projects.isEmpty()? null: projects.get(0);
	}

	@Override
	public void add(final Project project) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_PROJECT, new String[] { "id" });
				ps.setString(1, project.getTitle());
				ps.setString(2, project.getDescription());
				ps.setTimestamp(3, project.getStartDate());
				ps.setTimestamp(4, project.getEndDate());
				ps.setInt(5, project.getUniversity().getId());
				return ps;
			}
		}, keyHolder);
		project.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Project project) {
		jdbcTemplate.update(SQL_UPDATE_PROJECT, project.getTitle(), project.getDescription(), project.getStartDate(),
				project.getEndDate(), project.getUniversity().getId(), project.getId());
	}

	@Override
	public void delete(int id)  {
		jdbcTemplate.update(SQL_DELETE_PROJECT, id);
	}

	private static class ProjectMapper implements RowMapper<Project> {

		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project project = new Project();
			project.setId(rs.getInt("id"));
			project.setTitle(rs.getString("project_title"));
			project.setDescription(rs.getString("description"));
			project.setStartDate(rs.getTimestamp("start_date"));
			project.setEndDate(rs.getTimestamp("end_date"));
			University university = new University();
			university.setId(rs.getInt("university_id"));
			university.setTitle(rs.getString("university_title"));
			project.setUniversity(university);
			return project;
		}
	}
}