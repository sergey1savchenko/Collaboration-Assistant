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

import com.netcracker.ca.dao.ProjectStatusDao;
import com.netcracker.ca.model.ProjectStatus;

@Repository
public class ProjectStatusDaoImpl implements ProjectStatusDao {

	private static String SQL_SELECT_PROJECT_STATUS = "SELECT * FROM student_in_project_status_types";
	private static String SQL_SELECT_PROJECT_STATUS_BY_ID = SQL_SELECT_PROJECT_STATUS + " WHERE id=?";
	private static String SQL_SELECT_PROJECT_STATUS_BY_DESC = SQL_SELECT_PROJECT_STATUS + " WHERE description=?";
	private static String SQL_INSERT_PROJECT_STATUS = "INSERT INTO student_in_project_status_types (description) VALUES (?)";
	private static String SQL_UPDATE_PROJECT_STATUS = "UPDATE student_in_project_status_types SET description=? WHERE id=?";
	private static String SQL_DELETE_PROJECT_STATUS = "DELETE FROM student_in_project_status_types WHERE id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ProjectStatus getById(Integer id) {
		List<ProjectStatus> list = jdbcTemplate.query(SQL_SELECT_PROJECT_STATUS_BY_ID, new ProjectStatusRowMapper(),
				id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public ProjectStatus getByDesc(String desc) {
		List<ProjectStatus> list = jdbcTemplate.query(SQL_SELECT_PROJECT_STATUS_BY_DESC, new ProjectStatusRowMapper(),
				desc);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void add(final ProjectStatus status) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_PROJECT_STATUS, new String[] { "id" });
				ps.setString(1, status.getDescription());
				return ps;
			}
		}, keyHolder);
		status.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(ProjectStatus status) {
		jdbcTemplate.update(SQL_UPDATE_PROJECT_STATUS, status.getDescription(), status.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(SQL_DELETE_PROJECT_STATUS, id);
	}

	private static class ProjectStatusRowMapper implements RowMapper<ProjectStatus> {

		@Override
		public ProjectStatus mapRow(ResultSet rs, int n) throws SQLException {
			ProjectStatus ps = new ProjectStatus();
			ps.setId(rs.getInt("id"));
			ps.setDescription(rs.getString("description"));
			return ps;
		}
	}
}