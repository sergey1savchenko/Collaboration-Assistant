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

	private static final String SQL_SELECT_PROJECT = "SELECT p.id, p.title as p_title, p.description, p.start_date, p.end_date, p.university_id, un.title as un_title "
			+ "FROM projects AS p INNER JOIN universities AS un ON p.university_id = un.id";
	private static final String SQL_SELECT_ALL_PROJECTS_LIMITED = SQL_SELECT_PROJECT + " LIMIT ?,?";
	private static final String SQL_SELECT_PROJECT_BY_ID = SQL_SELECT_PROJECT + " WHERE p.id = ?";
	private static final String SQL_SELECT_PROJECT_BY_TITLE = SQL_SELECT_PROJECT + " WHERE p.title = ?";
	private static final String SQL_SELECT_PROJECT_FOR_ATTACHMENT = SQL_SELECT_PROJECT + " WHERE p.id = (SELECT project_id FROM attachments WHERE id=?)";
	private static final String SQL_SELECT_PROJECT_FOR_MEETING = SQL_SELECT_PROJECT + " WHERE p.id = (SELECT project_id FROM meetings WHERE id=?)";
	private static final String SQL_INSERT_PROJECT = "INSERT INTO projects (title, description, start_date, end_date, university_id) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_PROJECT = "UPDATE projects SET title = ?, description = ?, start_date = ?, end_date = ?, university_id = ? WHERE projects.id = ?";
	private static final String SQL_DELETE_PROJECT = "DELETE FROM projects WHERE projects.id = ?";
	private static final String SQL_COUNT = "SELECT COUNT(*) FROM projects";


	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Project> getAll() {
		return jdbcTemplate.query(SQL_SELECT_PROJECT, new ProjectMapper());
	}
	
	@Override
	public List<Project> getAll(int limit, int offset) {
		return jdbcTemplate.query(SQL_SELECT_ALL_PROJECTS_LIMITED, new ProjectMapper(), limit, offset);
	}

	@Override
	public Project getById(Integer id) {
		List<Project> projects = jdbcTemplate.query(SQL_SELECT_PROJECT_BY_ID, new ProjectMapper(), id);
		return projects.isEmpty()? null: projects.get(0);
	}
	
	@Override
	public Project getByTitle(String title) {
		List<Project> projects = jdbcTemplate.query(SQL_SELECT_PROJECT_BY_TITLE, new ProjectMapper(), title);
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
				ps.setDate(3, project.getStartDate());
				ps.setDate(4, project.getEndDate());
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
	public int count() {
		return jdbcTemplate.queryForObject(SQL_COUNT, Integer.class);
	}
	

	@Override
	public Project getForAttachment(int attachmentId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_PROJECT_FOR_ATTACHMENT, new ProjectMapper(), attachmentId);
	}
	

	@Override
	public Project getForMeeting(int meetingId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_PROJECT_FOR_MEETING, new ProjectMapper(), meetingId);
	}

	private static class ProjectMapper implements RowMapper<Project> {

		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project project = new Project();
			project.setId(rs.getInt("id"));
			project.setTitle(rs.getString("p_title"));
			project.setDescription(rs.getString("description"));
			project.setStartDate(rs.getDate("start_date"));
			project.setEndDate(rs.getDate("end_date"));
			University university = new University();
			university.setId(rs.getInt("university_id"));
			university.setTitle(rs.getString("un_title"));
			project.setUniversity(university);
			return project;
		}
	}

}