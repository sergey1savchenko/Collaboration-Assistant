package com.netcracker.dao.impl;

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

import com.netcracker.dao.RoleDAO;
import com.netcracker.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	private static String SQL_SELECT_ALL_ROLES = "SELECT * FROM roles";
	private static String SQL_INSERT_ROLE = "INSERT INTO roles (role) VALUES (?)";
	private static String SQL_UPDATE_ROLE = "UPDATE roles SET role=? WHERE id=?";
	private static String SQL_DELETE_ROLE = "DELETE FROM roles WHERE id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Role> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL_ROLES, new RoleRowMapper());
	}

	public void add(final Role role) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_ROLE);
				ps.setString(1, role.getName());
				return ps;
			}
		}, keyHolder);
		role.setId(keyHolder.getKey().intValue());
	}

	public void update(Role role) {
		jdbcTemplate.update(SQL_UPDATE_ROLE, role.getName(), role.getId());
	}

	public void delete(Role role) {
		jdbcTemplate.update(SQL_DELETE_ROLE, role.getId());
	}

	private static class RoleRowMapper implements RowMapper<Role> {

		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = new Role();
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("role"));
			return role;
		}
	}
}