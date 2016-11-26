package com.netcracker.ca.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.ProjectStatusDao;
import com.netcracker.ca.model.ProjectStatus;

@Repository
public class ProjectStatusDaoImpl implements ProjectStatusDao {
	
	private static String SQL_SELECT_PROJECT_STATUS = "SELECT * FROM student_in_project_status_types";
	private static String SQL_SELECT_PROJECT_STATUS_BY_DESC = SQL_SELECT_PROJECT_STATUS + " WHERE description=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ProjectStatus getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(ProjectStatus entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProjectStatus entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProjectStatus getByDesc(String desc) {
		List<ProjectStatus> list = jdbcTemplate.query(SQL_SELECT_PROJECT_STATUS_BY_DESC, new ProjectStatusRowMapper(), desc);
		return list.isEmpty() ? null: list.get(0);
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
