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

import com.netcracker.ca.dao.UserDao;
import com.netcracker.ca.model.Role;
import com.netcracker.ca.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static String SQL_SELECT_USER = "SELECT u.id AS u_id, u.email, u.first_name, u.second_name, u.last_name, u.is_active, r.id AS r_id, r.role "
			+ "FROM users AS u INNER JOIN user_roles AS ur ON u.id=ur.user_id INNER JOIN roles AS r ON ur.role_id=r.id";
	private static String SQL_SELECT_USER_BY_ID = SQL_SELECT_USER + " WHERE u.id=?";
	private static String SQL_SELECT_USER_BY_EMAIL = SQL_SELECT_USER + " WHERE email=?";
	private static String SQL_INSERT_USER = "INSERT INTO users (email, password, first_name, second_name, last_name, is_active) VALUES (?, ?, ?, ?, ?, ?)";
	private static String SQL_UPDATE_USER = "UPDATE SET email=?, password=?, first_name=?, second_name=?, last_name=?, is_active=? WHERE id=?";

	private static String SQL_INSERT_USER_ROLE = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
	private static String SQL_UPDATE_USER_ROLE = "UPDATE user_roles SET role_id=? WHERE user_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User getById(int id) {
		List<User> users = jdbcTemplate.query(SQL_SELECT_USER_BY_ID, new UserRowMapper(), id);
		return users.isEmpty() ? null : users.get(0);
	}

	public User getByEmail(String email) {
		List<User> users = jdbcTemplate.query(SQL_SELECT_USER_BY_EMAIL, new UserRowMapper(), email);
		return users.isEmpty() ? null : users.get(0);
	}

	public void add(final User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER, new String[] { "id" });
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getFirstName());
				ps.setString(4, user.getSecondName());
				ps.setString(5, user.getLastName());
				ps.setBoolean(6, user.isActive());
				return ps;
			}
		}, keyHolder);
		user.setId(keyHolder.getKey().intValue());
		jdbcTemplate.update(SQL_INSERT_USER_ROLE, user.getId(), user.getRole().getId());
	}

	public void update(User user) {
		jdbcTemplate.update(SQL_UPDATE_USER, user.getEmail(), user.getPassword(), user.getFirstName(),
				user.getSecondName(), user.getLastName(), user.isActive(), user.getId());
	}

	public void updateRole(User user) {
		jdbcTemplate.update(SQL_UPDATE_USER_ROLE, user.getRole().getId(), user.getId());
	}

	private static class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("u_id"));
			user.setEmail(rs.getString("email"));
			user.setFirstName(rs.getString("first_name"));
			user.setSecondName(rs.getString("second_name"));
			user.setLastName(rs.getString("last_name"));
			user.setActive(rs.getBoolean("is_active"));
			Role role = new Role();
			role.setId(rs.getInt("r_id"));
			role.setName(rs.getString("role"));
			user.setRole(role);
			return user;
		}
	}
}