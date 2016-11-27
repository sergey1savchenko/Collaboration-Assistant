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

import com.netcracker.ca.dao.ProjectDao;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.University;

@Repository
public class ProjectDaoImpl implements ProjectDao {

	private static final String SQL_SELECT_ALL_PROJECTS = "SELECT projects.id, projects.title as project_title, projects.description, projects.start_date, projects.end_date, projects.university_id, universities.title as university_title FROM (projects INNER JOIN universities ON projects.university_id = universities.id)";
	private static final String SQL_SELECT_PROJECT_BY_ID = SQL_SELECT_ALL_PROJECTS + " WHERE projects.id = ?";
	private static final String SQL_SELECT_PROJECT_BY_TITLE = SQL_SELECT_ALL_PROJECTS + " WHERE projects.title = ?";
	private static final String SQL_INSERT_PROJECT = "INSERT INTO projects (title, description, start_date, end_date, university_id) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_PROJECT = "UPDATE projects SET title = ?, description = ?, start_date = ?, end_date = ?, university_id = ? WHERE projects.id = ?";
	private static final String SQL_DELETE_PROJECT = "DELETE FROM projects WHERE projects.id = ?";
	
	private static final String SQL_INSERT_CURATOR = "INSERT INTO curators_in_project (user_id, project_id, team_id) VALUES (?, ?, ?)";
	private static final String SQL_DELETE_CURATOR = "DELETE FROM curators_in_project WHERE user_id=? AND project_id=?";
	
	private static final String SQL_SELECT_CURRENT_FOR_STUDENT = SQL_SELECT_ALL_PROJECTS + " INNER JOIN students_in_project AS sp ON projects.id=sp.project_id "
			+ "INNER JOIN application_forms AS af ON sp.app_form_id=af.id WHERE af.id=? AND projects.end_date>?";
	private static final String SQL_SELECT_CURRENT_FOR_CURATOR = SQL_SELECT_ALL_PROJECTS + " INNER JOIN curators_in_project AS cp ON projects.id=cp.project_id "
			+ "WHERE cp.user_id=? AND projects.end_date>?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Project> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL_PROJECTS, new ProjectMapper());
	}

	@Override
	public Project getById(Integer id) {
		List<Project> projects = jdbcTemplate.query(SQL_SELECT_PROJECT_BY_ID, new ProjectMapper(), id);
		return projects.isEmpty()? null: projects.get(0);
	}
	
	@Override
	public Project getByTitle(String title) {
		return jdbcTemplate.queryForObject(SQL_SELECT_PROJECT_BY_TITLE, new ProjectMapper(), title);
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
	public void delete(Integer id)  {
		jdbcTemplate.update(SQL_DELETE_PROJECT, id);
	}
	
	@Override
	public void addCurator(int curatorId, int projectId, int teamId) {
		jdbcTemplate.update(SQL_INSERT_CURATOR, curatorId, projectId, teamId);
	}
	
	@Override
	public void removeCurator(int curatorId, int projectId) {
		jdbcTemplate.update(SQL_DELETE_CURATOR, curatorId, projectId);
	}
	
	@Override
	public Project getCurrentForStudent(int studentId) {
		List<Project> projects = jdbcTemplate.query(SQL_SELECT_CURRENT_FOR_STUDENT, new ProjectMapper(), studentId, new Date());
		return projects.isEmpty() ? null: projects.get(0);
	}

	@Override
	public Project getCurrentForCurator(int curatorId) {
		List<Project> projects = jdbcTemplate.query(SQL_SELECT_CURRENT_FOR_CURATOR, new ProjectMapper(), curatorId, new Date());
		return projects.isEmpty() ? null: projects.get(0);
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

	@Override
	public List<Project> getAll(int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsWithTitle(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	

}