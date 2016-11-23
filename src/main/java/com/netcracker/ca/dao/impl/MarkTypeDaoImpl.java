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

import com.netcracker.ca.dao.MarkTypeDao;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MarkTypeScope;

@Repository
public class MarkTypeDaoImpl implements MarkTypeDao {

	private static String SQL_SELECT_MARK_TYPE_BY_ID = "SELECT marktypes.id, marktypes.title, marktypes.has_text, marktypes.has_int, allow_marktypes.scope FROM marktypes INNER JOIN allow_marktypes ON marktypes.id = allow_marktypes.marktype_id WHERE allow_marktypes.project_id=?";
	private static String SQL_INSERT_MARK_TYPE = "INSERT INTO marktypes (title, has_text, has_int) VALUES (?, ?, ?)";
	private static String SQL_UPDATE_MARK_TYPE = "UPDATE marktypes SET title=?, has_text=?, has_int=? WHERE id=?";
	private static String SQL_DELETE_MARK_TYPE = "DELETE FROM marktypes WHERE id=?";
	private static String SQL_INSERT_ALLOW_MARKTYPE = "INSERT INTO allow_marktypes (project_id, marktype_id, scope) VALUES (?, ?, ?)";
	private static String SQL_DELETE_ALLOW_MARKTYPE = "DELETE FROM allow_marktypes WHERE project_id=? AND marktype_id=? AND scope=?";
	private static String SQL_SELECT_ALLOWED_FOR_PROJECT = "SELECT m.id, m.title, m.has_text, m.has_int FROM marktypes AS m "
			+ "INNER JOIN allow_marktypes AS am ON m.id=am.marktype_id AND am.project_id=? AND am.scope=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MarkType getById(Integer id) {
		List<MarkType> markTypes = jdbcTemplate.query(SQL_SELECT_MARK_TYPE_BY_ID, new MarkTypeRowMapper(), id);
		return markTypes.isEmpty()? null: markTypes.get(0);
	}

	public void add(final MarkType markType) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_MARK_TYPE, new String[] { "id" });
				ps.setString(1, markType.getTitle());
				ps.setBoolean(2, markType.hasText());
				ps.setBoolean(3, markType.hasInt());
				return ps;
			}
		}, keyHolder);
		markType.setId(keyHolder.getKey().intValue());
	}

	public void update(MarkType markType) {
		jdbcTemplate.update(SQL_UPDATE_MARK_TYPE, markType.getTitle(), markType.hasText(), markType.hasInt(),
				markType.getId());
	}

	public void delete(Integer markTypeId) {
		jdbcTemplate.update(SQL_DELETE_MARK_TYPE, markTypeId);
	}

	public void allow(int markTypeId, int projectId, MarkTypeScope scope) {
		jdbcTemplate.update(SQL_INSERT_ALLOW_MARKTYPE, projectId, markTypeId, scope.ordinal());
	}

	public void disallow(int markTypeId, int projectId,  MarkTypeScope scope) {
		jdbcTemplate.update(SQL_DELETE_ALLOW_MARKTYPE, projectId,markTypeId, scope.ordinal());
	}

	public List<MarkType> getAllowed(int projectId, MarkTypeScope scope) {
		return jdbcTemplate.query(SQL_SELECT_ALLOWED_FOR_PROJECT, new MarkTypeRowMapper(), projectId,
				scope.ordinal());
	}

	private static class MarkTypeRowMapper implements RowMapper<MarkType> {

		public MarkType mapRow(ResultSet rs, int rowNum) throws SQLException {
			MarkType markType = new MarkType();
			markType.setId(rs.getInt("id"));
			markType.setTitle(rs.getString("title"));
			markType.setHasText(rs.getBoolean("has_text"));
			markType.setHasInt(rs.getBoolean("has_int"));
			markType.setScope(MarkTypeScope.values()[rs.getInt("scope")]);
			return markType;
		}
	}
}